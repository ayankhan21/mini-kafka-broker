package com.broker.grpc;

import com.broker.partition.PartitionManager;
import com.proto.ProducerProto;
import com.proto.ProducerServiceGrpc;
import io.grpc.stub.StreamObserver;

public class ProducerServiceImpl extends ProducerServiceGrpc.ProducerServiceImplBase {

    private final PartitionManager partitionManager;

    public ProducerServiceImpl(PartitionManager partitionManager) {
        this.partitionManager = partitionManager;
    }

    @Override
    public StreamObserver<ProducerProto.ProducerEvent> streamEvents(StreamObserver<ProducerProto.Ack> responseObserver) {

        return new StreamObserver<ProducerProto.ProducerEvent>() {

            int eventCount = 0;

            @Override
            public void onNext(ProducerProto.ProducerEvent event) {
                eventCount++;
                // Hand event off to PartitionManager — assigns to correct partition
                partitionManager.assign(
                        event.getKey(),
                        event.getValue(),
                        event.getTimestamp()
                );
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Producer stream error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Producer stream ended. Total events received: " + eventCount);
                responseObserver.onNext(
                        ProducerProto.Ack.newBuilder()
                                .setSuccess(true)
                                .setMessage("Broker received " + eventCount + " events.")
                                .build()
                );
                responseObserver.onCompleted();
            }
        };
    }
}