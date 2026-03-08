package com.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: producer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProducerServiceGrpc {

  private ProducerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ProducerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.ProducerProto.ProducerEvent,
      com.proto.ProducerProto.Ack> getStreamEventsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamEvents",
      requestType = com.proto.ProducerProto.ProducerEvent.class,
      responseType = com.proto.ProducerProto.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.ProducerProto.ProducerEvent,
      com.proto.ProducerProto.Ack> getStreamEventsMethod() {
    io.grpc.MethodDescriptor<com.proto.ProducerProto.ProducerEvent, com.proto.ProducerProto.Ack> getStreamEventsMethod;
    if ((getStreamEventsMethod = ProducerServiceGrpc.getStreamEventsMethod) == null) {
      synchronized (ProducerServiceGrpc.class) {
        if ((getStreamEventsMethod = ProducerServiceGrpc.getStreamEventsMethod) == null) {
          ProducerServiceGrpc.getStreamEventsMethod = getStreamEventsMethod =
              io.grpc.MethodDescriptor.<com.proto.ProducerProto.ProducerEvent, com.proto.ProducerProto.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamEvents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.ProducerProto.ProducerEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.ProducerProto.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new ProducerServiceMethodDescriptorSupplier("StreamEvents"))
              .build();
        }
      }
    }
    return getStreamEventsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProducerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProducerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProducerServiceStub>() {
        @java.lang.Override
        public ProducerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProducerServiceStub(channel, callOptions);
        }
      };
    return ProducerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProducerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProducerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProducerServiceBlockingStub>() {
        @java.lang.Override
        public ProducerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProducerServiceBlockingStub(channel, callOptions);
        }
      };
    return ProducerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProducerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProducerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProducerServiceFutureStub>() {
        @java.lang.Override
        public ProducerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProducerServiceFutureStub(channel, callOptions);
        }
      };
    return ProducerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Client-side streaming: producer continuously streams events to broker
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.proto.ProducerProto.ProducerEvent> streamEvents(
        io.grpc.stub.StreamObserver<com.proto.ProducerProto.Ack> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamEventsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProducerService.
   */
  public static abstract class ProducerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProducerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProducerService.
   */
  public static final class ProducerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProducerServiceStub> {
    private ProducerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProducerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProducerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client-side streaming: producer continuously streams events to broker
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.proto.ProducerProto.ProducerEvent> streamEvents(
        io.grpc.stub.StreamObserver<com.proto.ProducerProto.Ack> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getStreamEventsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProducerService.
   */
  public static final class ProducerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProducerServiceBlockingStub> {
    private ProducerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProducerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProducerServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProducerService.
   */
  public static final class ProducerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProducerServiceFutureStub> {
    private ProducerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProducerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProducerServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STREAM_EVENTS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_EVENTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamEvents(
              (io.grpc.stub.StreamObserver<com.proto.ProducerProto.Ack>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getStreamEventsMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.proto.ProducerProto.ProducerEvent,
              com.proto.ProducerProto.Ack>(
                service, METHODID_STREAM_EVENTS)))
        .build();
  }

  private static abstract class ProducerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProducerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.ProducerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProducerService");
    }
  }

  private static final class ProducerServiceFileDescriptorSupplier
      extends ProducerServiceBaseDescriptorSupplier {
    ProducerServiceFileDescriptorSupplier() {}
  }

  private static final class ProducerServiceMethodDescriptorSupplier
      extends ProducerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProducerServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProducerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProducerServiceFileDescriptorSupplier())
              .addMethod(getStreamEventsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
