package generated.grpc.smartlearningresource;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: smart_learning_resource.proto")
public final class SmartLearningResourceServiceGrpc {

  private SmartLearningResourceServiceGrpc() {}

  public static final String SERVICE_NAME = "SmartLearningResourceGrpc.SmartLearningResourceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartlearningresource.ResourceRequest,
      generated.grpc.smartlearningresource.ResourceStatus> getGetResourceAvailabilityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetResourceAvailability",
      requestType = generated.grpc.smartlearningresource.ResourceRequest.class,
      responseType = generated.grpc.smartlearningresource.ResourceStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.smartlearningresource.ResourceRequest,
      generated.grpc.smartlearningresource.ResourceStatus> getGetResourceAvailabilityMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartlearningresource.ResourceRequest, generated.grpc.smartlearningresource.ResourceStatus> getGetResourceAvailabilityMethod;
    if ((getGetResourceAvailabilityMethod = SmartLearningResourceServiceGrpc.getGetResourceAvailabilityMethod) == null) {
      synchronized (SmartLearningResourceServiceGrpc.class) {
        if ((getGetResourceAvailabilityMethod = SmartLearningResourceServiceGrpc.getGetResourceAvailabilityMethod) == null) {
          SmartLearningResourceServiceGrpc.getGetResourceAvailabilityMethod = getGetResourceAvailabilityMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartlearningresource.ResourceRequest, generated.grpc.smartlearningresource.ResourceStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SmartLearningResourceGrpc.SmartLearningResourceService", "GetResourceAvailability"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartlearningresource.ResourceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartlearningresource.ResourceStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartLearningResourceServiceMethodDescriptorSupplier("GetResourceAvailability"))
                  .build();
          }
        }
     }
     return getGetResourceAvailabilityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartlearningresource.CategoryRequest,
      generated.grpc.smartlearningresource.ResourceInfo> getStreamAvailableResourcesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamAvailableResources",
      requestType = generated.grpc.smartlearningresource.CategoryRequest.class,
      responseType = generated.grpc.smartlearningresource.ResourceInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.smartlearningresource.CategoryRequest,
      generated.grpc.smartlearningresource.ResourceInfo> getStreamAvailableResourcesMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartlearningresource.CategoryRequest, generated.grpc.smartlearningresource.ResourceInfo> getStreamAvailableResourcesMethod;
    if ((getStreamAvailableResourcesMethod = SmartLearningResourceServiceGrpc.getStreamAvailableResourcesMethod) == null) {
      synchronized (SmartLearningResourceServiceGrpc.class) {
        if ((getStreamAvailableResourcesMethod = SmartLearningResourceServiceGrpc.getStreamAvailableResourcesMethod) == null) {
          SmartLearningResourceServiceGrpc.getStreamAvailableResourcesMethod = getStreamAvailableResourcesMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartlearningresource.CategoryRequest, generated.grpc.smartlearningresource.ResourceInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartLearningResourceGrpc.SmartLearningResourceService", "StreamAvailableResources"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartlearningresource.CategoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartlearningresource.ResourceInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartLearningResourceServiceMethodDescriptorSupplier("StreamAvailableResources"))
                  .build();
          }
        }
     }
     return getStreamAvailableResourcesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SmartLearningResourceServiceStub newStub(io.grpc.Channel channel) {
    return new SmartLearningResourceServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartLearningResourceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SmartLearningResourceServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SmartLearningResourceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SmartLearningResourceServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SmartLearningResourceServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * checks whether a specific resource is available.
     * </pre>
     */
    public void getResourceAvailability(generated.grpc.smartlearningresource.ResourceRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartlearningresource.ResourceStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getGetResourceAvailabilityMethod(), responseObserver);
    }

    /**
     * <pre>
     * SERVER STREAMING
     * Streams all available resources within a given category.
     * </pre>
     */
    public void streamAvailableResources(generated.grpc.smartlearningresource.CategoryRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartlearningresource.ResourceInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamAvailableResourcesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetResourceAvailabilityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.smartlearningresource.ResourceRequest,
                generated.grpc.smartlearningresource.ResourceStatus>(
                  this, METHODID_GET_RESOURCE_AVAILABILITY)))
          .addMethod(
            getStreamAvailableResourcesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                generated.grpc.smartlearningresource.CategoryRequest,
                generated.grpc.smartlearningresource.ResourceInfo>(
                  this, METHODID_STREAM_AVAILABLE_RESOURCES)))
          .build();
    }
  }

  /**
   */
  public static final class SmartLearningResourceServiceStub extends io.grpc.stub.AbstractStub<SmartLearningResourceServiceStub> {
    private SmartLearningResourceServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartLearningResourceServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartLearningResourceServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartLearningResourceServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * checks whether a specific resource is available.
     * </pre>
     */
    public void getResourceAvailability(generated.grpc.smartlearningresource.ResourceRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartlearningresource.ResourceStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetResourceAvailabilityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SERVER STREAMING
     * Streams all available resources within a given category.
     * </pre>
     */
    public void streamAvailableResources(generated.grpc.smartlearningresource.CategoryRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartlearningresource.ResourceInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamAvailableResourcesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SmartLearningResourceServiceBlockingStub extends io.grpc.stub.AbstractStub<SmartLearningResourceServiceBlockingStub> {
    private SmartLearningResourceServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartLearningResourceServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartLearningResourceServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartLearningResourceServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * checks whether a specific resource is available.
     * </pre>
     */
    public generated.grpc.smartlearningresource.ResourceStatus getResourceAvailability(generated.grpc.smartlearningresource.ResourceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetResourceAvailabilityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SERVER STREAMING
     * Streams all available resources within a given category.
     * </pre>
     */
    public java.util.Iterator<generated.grpc.smartlearningresource.ResourceInfo> streamAvailableResources(
        generated.grpc.smartlearningresource.CategoryRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamAvailableResourcesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SmartLearningResourceServiceFutureStub extends io.grpc.stub.AbstractStub<SmartLearningResourceServiceFutureStub> {
    private SmartLearningResourceServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartLearningResourceServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartLearningResourceServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartLearningResourceServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * checks whether a specific resource is available.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.smartlearningresource.ResourceStatus> getResourceAvailability(
        generated.grpc.smartlearningresource.ResourceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetResourceAvailabilityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RESOURCE_AVAILABILITY = 0;
  private static final int METHODID_STREAM_AVAILABLE_RESOURCES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SmartLearningResourceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SmartLearningResourceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RESOURCE_AVAILABILITY:
          serviceImpl.getResourceAvailability((generated.grpc.smartlearningresource.ResourceRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.smartlearningresource.ResourceStatus>) responseObserver);
          break;
        case METHODID_STREAM_AVAILABLE_RESOURCES:
          serviceImpl.streamAvailableResources((generated.grpc.smartlearningresource.CategoryRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.smartlearningresource.ResourceInfo>) responseObserver);
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

  private static abstract class SmartLearningResourceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartLearningResourceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.smartlearningresource.SmartLearningResourceServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartLearningResourceService");
    }
  }

  private static final class SmartLearningResourceServiceFileDescriptorSupplier
      extends SmartLearningResourceServiceBaseDescriptorSupplier {
    SmartLearningResourceServiceFileDescriptorSupplier() {}
  }

  private static final class SmartLearningResourceServiceMethodDescriptorSupplier
      extends SmartLearningResourceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmartLearningResourceServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SmartLearningResourceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SmartLearningResourceServiceFileDescriptorSupplier())
              .addMethod(getGetResourceAvailabilityMethod())
              .addMethod(getStreamAvailableResourcesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
