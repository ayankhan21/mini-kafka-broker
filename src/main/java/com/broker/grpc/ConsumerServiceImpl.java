package com.broker.grpc;

import com.broker.partition.Event;
import com.broker.partition.PartitionManager;
import com.proto.ConsumerProto;
import com.proto.ConsumerServiceGrpc;
import io.grpc.stub.StreamObserver;

public class ConsumerServiceImpl extends ConsumerServiceGrpc.ConsumerServiceImplBase {

    private final PartitionManager partitionManager;

    public ConsumerServiceImpl(PartitionManager partitionManager) {
        this.partitionManager = partitionManager;
    }

    @Override
    public void subscribeToPartition(ConsumerProto.SubscribeRequest request,
                                     StreamObserver<ConsumerProto.Event> responseObserver) {

        int partitionId = request.getPartition();
        String consumerGroup = request.getConsumerGroup();
        String consumerName = request.getConsumerName();
        long offset = request.getOffset();

        System.out.println(consumerName + " (" + consumerGroup + ") subscribed to partition " + partitionId + " from offset " + offset);

        // Each subscription runs in its own thread
        // Otherwise this would block the gRPC thread and no other consumers could connect
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    // Blocks until an event is available in this partition
                    Event event = partitionManager.poll(partitionId);

                    // Build the proto Event to send back to the consumer
                    ConsumerProto.Event protoEvent = ConsumerProto.Event.newBuilder()
                            .setKey(event.getKey())
                            .setValue(event.getValue())
                            .setOffset(event.getOffset())
                            .setPartition(event.getPartition())
                            .setTimestamp(event.getTimestamp())
                            .build();

                    // Stream event to consumer
                    responseObserver.onNext(protoEvent);
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer thread interrupted for partition " + partitionId);
                responseObserver.onCompleted();
            }
        });

        consumerThread.setDaemon(true); // dies when broker shuts down
        consumerThread.start();
    }
}