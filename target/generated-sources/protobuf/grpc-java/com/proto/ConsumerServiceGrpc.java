package com.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: consumer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ConsumerServiceGrpc {

  private ConsumerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ConsumerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.ConsumerProto.SubscribeRequest,
      com.proto.ConsumerProto.Event> getSubscribeToPartitionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeToPartition",
      requestType = com.proto.ConsumerProto.SubscribeRequest.class,
      responseType = com.proto.ConsumerProto.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.ConsumerProto.SubscribeRequest,
      com.proto.ConsumerProto.Event> getSubscribeToPartitionMethod() {
    io.grpc.MethodDescriptor<com.proto.ConsumerProto.SubscribeRequest, com.proto.ConsumerProto.Event> getSubscribeToPartitionMethod;
    if ((getSubscribeToPartitionMethod = ConsumerServiceGrpc.getSubscribeToPartitionMethod) == null) {
      synchronized (ConsumerServiceGrpc.class) {
        if ((getSubscribeToPartitionMethod = ConsumerServiceGrpc.getSubscribeToPartitionMethod) == null) {
          ConsumerServiceGrpc.getSubscribeToPartitionMethod = getSubscribeToPartitionMethod =
              io.grpc.MethodDescriptor.<com.proto.ConsumerProto.SubscribeRequest, com.proto.ConsumerProto.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeToPartition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.ConsumerProto.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.ConsumerProto.Event.getDefaultInstance()))
              .setSchemaDescriptor(new ConsumerServiceMethodDescriptorSupplier("SubscribeToPartition"))
              .build();
        }
      }
    }
    return getSubscribeToPartitionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConsumerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConsumerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConsumerServiceStub>() {
        @java.lang.Override
        public ConsumerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConsumerServiceStub(channel, callOptions);
        }
      };
    return ConsumerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConsumerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConsumerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConsumerServiceBlockingStub>() {
        @java.lang.Override
        public ConsumerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConsumerServiceBlockingStub(channel, callOptions);
        }
      };
    return ConsumerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConsumerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConsumerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConsumerServiceFutureStub>() {
        @java.lang.Override
        public ConsumerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConsumerServiceFutureStub(channel, callOptions);
        }
      };
    return ConsumerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Server-side streaming: consumer subscribes to a partition, broker streams events as they arrive
     * </pre>
     */
    default void subscribeToPartition(com.proto.ConsumerProto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<com.proto.ConsumerProto.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeToPartitionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ConsumerService.
   */
  public static abstract class ConsumerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ConsumerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ConsumerService.
   */
  public static final class ConsumerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ConsumerServiceStub> {
    private ConsumerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConsumerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConsumerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Server-side streaming: consumer subscribes to a partition, broker streams events as they arrive
     * </pre>
     */
    public void subscribeToPartition(com.proto.ConsumerProto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<com.proto.ConsumerProto.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeToPartitionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ConsumerService.
   */
  public static final class ConsumerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ConsumerServiceBlockingStub> {
    private ConsumerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConsumerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConsumerServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Server-side streaming: consumer subscribes to a partition, broker streams events as they arrive
     * </pre>
     */
    public java.util.Iterator<com.proto.ConsumerProto.Event> subscribeToPartition(
        com.proto.ConsumerProto.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeToPartitionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ConsumerService.
   */
  public static final class ConsumerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ConsumerServiceFutureStub> {
    private ConsumerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConsumerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConsumerServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_TO_PARTITION = 0;

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
        case METHODID_SUBSCRIBE_TO_PARTITION:
          serviceImpl.subscribeToPartition((com.proto.ConsumerProto.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.ConsumerProto.Event>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubscribeToPartitionMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.proto.ConsumerProto.SubscribeRequest,
              com.proto.ConsumerProto.Event>(
                service, METHODID_SUBSCRIBE_TO_PARTITION)))
        .build();
  }

  private static abstract class ConsumerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConsumerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.ConsumerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConsumerService");
    }
  }

  private static final class ConsumerServiceFileDescriptorSupplier
      extends ConsumerServiceBaseDescriptorSupplier {
    ConsumerServiceFileDescriptorSupplier() {}
  }

  private static final class ConsumerServiceMethodDescriptorSupplier
      extends ConsumerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ConsumerServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ConsumerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConsumerServiceFileDescriptorSupplier())
              .addMethod(getSubscribeToPartitionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
