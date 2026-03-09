package com.broker.http;

import com.broker.partition.PartitionManager;
import com.sun.net.httpserver.HttpServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class ConfigHttpServer {

    // AtomicReference so PartitionManager can be safely set from a POST request
    // and read by gRPC threads concurrently
    private final AtomicReference<PartitionManager> partitionManagerRef;

    public ConfigHttpServer(AtomicReference<PartitionManager> partitionManagerRef) {
        this.partitionManagerRef = partitionManagerRef;
    }

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // GET /config — returns current config if initialized
        server.createContext("/config", exchange -> {

            // Handle CORS preflight
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                exchange.getResponseBody().close();
                return;
            }

            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                PartitionManager pm = partitionManagerRef.get();
                String response;
                int statusCode;

                if (pm == null) {
                    response = "{ \"error\": \"Broker not initialized yet. POST /config first.\" }";
                    statusCode = 503;
                } else {
                    response = "{ \"partitionCount\": " + pm.getPartitionCount() + ", \"topic\": \"payments\" }";
                    statusCode = 200;
                }

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(statusCode, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                // POST /config — initializes PartitionManager with given partition count
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                System.out.println("POST /config received. Body will follow...");
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                // Parse partitionCount from body manually (no JSON lib needed)
                // expects: { "partitionCount": 3 }
                String response;
                int statusCode;

                try {
                    int partitionCount = extractPartitionCount(body);
                    if (partitionCount < 1 || partitionCount > 5) {
                        throw new IllegalArgumentException("partitionCount must be between 1 and 5");
                    }
                    partitionManagerRef.set(new PartitionManager(partitionCount));
                    response = "{ \"message\": \"Broker initialized with " + partitionCount + " partitions\" }";
                    statusCode = 200;
                    System.out.println("Broker initialized with " + partitionCount + " partitions via POST /config");
                } catch (Exception e) {
                    response = "{ \"error\": \"" + e.getMessage() + "\" }";
                    statusCode = 400;
                }

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(statusCode, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.start();
        System.out.println("Config HTTP server started on port 8080");
    }

    // Simple manual parse — avoids pulling in a JSON library
    private int extractPartitionCount(String json) {
        String key = "\"partitionCount\"";
        int keyIndex = json.indexOf(key);
        if (keyIndex == -1) throw new IllegalArgumentException("Missing partitionCount field");
        String after = json.substring(keyIndex + key.length());
        String digits = after.replaceAll("[^0-9]", "").trim();
        return Integer.parseInt(digits.substring(0, 1));
    }
}