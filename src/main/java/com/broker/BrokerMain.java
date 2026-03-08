package com.broker;

import com.broker.grpc.ConsumerServiceImpl;
import com.broker.grpc.ProducerServiceImpl;
import com.broker.http.ConfigHttpServer;
import com.broker.partition.PartitionManager;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.concurrent.atomic.AtomicReference;

public class BrokerMain {

    public static void main(String[] args) throws Exception {

        // Shared reference — null until dashboard POSTs /config
        AtomicReference<PartitionManager> partitionManagerRef = new AtomicReference<>(new PartitionManager(3));

        // Start REST server — handles POST /config to initialize broker
        ConfigHttpServer configHttpServer = new ConfigHttpServer(partitionManagerRef);
        configHttpServer.start();

        // Start gRPC server — services will reject requests until partitionManager is set
        Server grpcServer = ServerBuilder.forPort(9090)
                .addService(new ProducerServiceImpl(partitionManagerRef))
                .addService(new ConsumerServiceImpl(partitionManagerRef))
                .build();

        grpcServer.start();
        System.out.println("Broker started with default 3 partitions. Override via POST /config before starting stream.");
        System.out.println("Waiting for partition config via POST /config on port 8080...");

        grpcServer.awaitTermination();
    }
}