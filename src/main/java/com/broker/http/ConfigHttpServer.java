package com.broker.http;

import com.broker.partition.PartitionManager;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ConfigHttpServer {

    private final PartitionManager partitionManager;

    public ConfigHttpServer(PartitionManager partitionManager) {
        this.partitionManager = partitionManager;
    }

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/config", exchange -> {
            String response = "{ \"partitionCount\": " + partitionManager.getPartitionCount() + ", \"topic\": \"payments\" }";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Config HTTP server started on port 8080");
    }
}