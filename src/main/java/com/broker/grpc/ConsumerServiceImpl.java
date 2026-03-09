package com.broker.grpc;

import com.broker.partition.Event;
import com.broker.partition.PartitionManager;
import com.proto.ConsumerProto;
import com.proto.ConsumerServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicReference;

public class ConsumerServiceImpl extends ConsumerServiceGrpc.ConsumerServiceImplBase {

    private final AtomicReference<PartitionManager> partitionManagerRef;

    public ConsumerServiceImpl(AtomicReference<PartitionManager> partitionManagerRef) {
        this.partitionManagerRef = partitionManagerRef;
    }

    @Override
    public void subscribeToPartition(ConsumerProto.SubscribeRequest request,
                                     StreamObserver<ConsumerProto.Event> responseObserver) {

        PartitionManager partitionManager = partitionManagerRef.get();
        if (partitionManager == null) {
            responseObserver.onError(new RuntimeException("Broker not initialized yet. POST /config first."));
            return;
        }

        int partitionId = request.getPartition();
        String consumerGroup = request.getConsumerGroup();
        String consumerName = request.getConsumerName();
        long offset = request.getOffset();

        System.out.println(consumerName + " (" + consumerGroup + ") subscribed to partition " + partitionId + " from offset " + offset);

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    Event event = partitionManager.poll(partitionId);

                    ConsumerProto.Event protoEvent = ConsumerProto.Event.newBuilder()
                            .setKey(event.getKey())
                            .setValue(event.getValue())
                            .setOffset(event.getOffset())
                            .setPartition(event.getPartition())
                            .setTimestamp(event.getTimestamp())
                            .build();

                    try {
                        responseObserver.onNext(protoEvent);
                    } catch (Exception e) {
                        // Consumer disconnected, stop this thread silently
                        System.out.println("Consumer disconnected from partition " + partitionId + ", stopping stream.");
                        return;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer thread interrupted for partition " + partitionId);
                responseObserver.onCompleted();
            }
        });

        consumerThread.setDaemon(true);
        consumerThread.start();
    }
}