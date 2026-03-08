package com.broker.grpc;

import com.broker.partition.PartitionManager;
import com.proto.ProducerProto;
import com.proto.ProducerServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicReference;

public class ProducerServiceImpl extends ProducerServiceGrpc.ProducerServiceImplBase {

    private final AtomicReference<PartitionManager> partitionManagerRef;

    public ProducerServiceImpl(AtomicReference<PartitionManager> partitionManagerRef) {
        this.partitionManagerRef = partitionManagerRef;
    }

    @Override
    public StreamObserver<ProducerProto.ProducerEvent> streamEvents(StreamObserver<ProducerProto.Ack> responseObserver) {

        return new StreamObserver<ProducerProto.ProducerEvent>() {

            int eventCount = 0;

            @Override
            public void onNext(ProducerProto.ProducerEvent event) {
                PartitionManager pm = partitionManagerRef.get();
                if (pm == null) {
                    responseObserver.onError(new RuntimeException("Broker not initialized yet. POST /config first."));
                    return;
                }
                eventCount++;
                pm.assign(event.getKey(), event.getValue(), event.getTimestamp());
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