package com.broker;

import com.broker.grpc.ConsumerServiceImpl;
import com.broker.grpc.ProducerServiceImpl;
import com.broker.http.ConfigHttpServer;
import com.broker.partition.PartitionManager;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class BrokerMain {

    public static void main(String[] args) throws Exception {

        // Read partition count from env, default to 3
        int partitionCount = Integer.parseInt(
                System.getenv().getOrDefault("PARTITION_COUNT", "3")
        );

        // 1. Initialize partition manager — creates N BlockingQueues
        PartitionManager partitionManager = new PartitionManager(partitionCount);

        // 2. Start REST server for GET /config
        ConfigHttpServer configHttpServer = new ConfigHttpServer(partitionManager);
        configHttpServer.start();

        // 3. Start gRPC server with both services registered
        Server grpcServer = ServerBuilder.forPort(9090)
                .addService(new ProducerServiceImpl(partitionManager))
                .addService(new ConsumerServiceImpl(partitionManager))
                .build();

        grpcServer.start();
        System.out.println("Broker gRPC server started on port 9090");

        // 4. Keep alive until killed
        grpcServer.awaitTermination();
    }
}