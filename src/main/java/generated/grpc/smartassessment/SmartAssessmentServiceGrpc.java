package generated.grpc.smartassessment;

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
    comments = "Source: smart_assessment.proto")
public final class SmartAssessmentServiceGrpc {

  private SmartAssessmentServiceGrpc() {}

  public static final String SERVICE_NAME = "SmartAssessmentGrpc.SmartAssessmentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartassessment.AssessmentRequest,
      generated.grpc.smartassessment.AssessmentInfo> getGetAssessmentDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssessmentDetails",
      requestType = generated.grpc.smartassessment.AssessmentRequest.class,
      responseType = generated.grpc.smartassessment.AssessmentInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.smartassessment.AssessmentRequest,
      generated.grpc.smartassessment.AssessmentInfo> getGetAssessmentDetailsMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartassessment.AssessmentRequest, generated.grpc.smartassessment.AssessmentInfo> getGetAssessmentDetailsMethod;
    if ((getGetAssessmentDetailsMethod = SmartAssessmentServiceGrpc.getGetAssessmentDetailsMethod) == null) {
      synchronized (SmartAssessmentServiceGrpc.class) {
        if ((getGetAssessmentDetailsMethod = SmartAssessmentServiceGrpc.getGetAssessmentDetailsMethod) == null) {
          SmartAssessmentServiceGrpc.getGetAssessmentDetailsMethod = getGetAssessmentDetailsMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartassessment.AssessmentRequest, generated.grpc.smartassessment.AssessmentInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SmartAssessmentGrpc.SmartAssessmentService", "GetAssessmentDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.AssessmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.AssessmentInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartAssessmentServiceMethodDescriptorSupplier("GetAssessmentDetails"))
                  .build();
          }
        }
     }
     return getGetAssessmentDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartassessment.AnswerSubmission,
      generated.grpc.smartassessment.SubmissionSummary> getSubmitAssessmentAnswersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitAssessmentAnswers",
      requestType = generated.grpc.smartassessment.AnswerSubmission.class,
      responseType = generated.grpc.smartassessment.SubmissionSummary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.smartassessment.AnswerSubmission,
      generated.grpc.smartassessment.SubmissionSummary> getSubmitAssessmentAnswersMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartassessment.AnswerSubmission, generated.grpc.smartassessment.SubmissionSummary> getSubmitAssessmentAnswersMethod;
    if ((getSubmitAssessmentAnswersMethod = SmartAssessmentServiceGrpc.getSubmitAssessmentAnswersMethod) == null) {
      synchronized (SmartAssessmentServiceGrpc.class) {
        if ((getSubmitAssessmentAnswersMethod = SmartAssessmentServiceGrpc.getSubmitAssessmentAnswersMethod) == null) {
          SmartAssessmentServiceGrpc.getSubmitAssessmentAnswersMethod = getSubmitAssessmentAnswersMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartassessment.AnswerSubmission, generated.grpc.smartassessment.SubmissionSummary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartAssessmentGrpc.SmartAssessmentService", "SubmitAssessmentAnswers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.AnswerSubmission.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.SubmissionSummary.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartAssessmentServiceMethodDescriptorSupplier("SubmitAssessmentAnswers"))
                  .build();
          }
        }
     }
     return getSubmitAssessmentAnswersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartassessment.ResultRequest,
      generated.grpc.smartassessment.StudentResult> getStreamAssessmentResultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamAssessmentResults",
      requestType = generated.grpc.smartassessment.ResultRequest.class,
      responseType = generated.grpc.smartassessment.StudentResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.smartassessment.ResultRequest,
      generated.grpc.smartassessment.StudentResult> getStreamAssessmentResultsMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartassessment.ResultRequest, generated.grpc.smartassessment.StudentResult> getStreamAssessmentResultsMethod;
    if ((getStreamAssessmentResultsMethod = SmartAssessmentServiceGrpc.getStreamAssessmentResultsMethod) == null) {
      synchronized (SmartAssessmentServiceGrpc.class) {
        if ((getStreamAssessmentResultsMethod = SmartAssessmentServiceGrpc.getStreamAssessmentResultsMethod) == null) {
          SmartAssessmentServiceGrpc.getStreamAssessmentResultsMethod = getStreamAssessmentResultsMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartassessment.ResultRequest, generated.grpc.smartassessment.StudentResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartAssessmentGrpc.SmartAssessmentService", "StreamAssessmentResults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.ResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.StudentResult.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartAssessmentServiceMethodDescriptorSupplier("StreamAssessmentResults"))
                  .build();
          }
        }
     }
     return getStreamAssessmentResultsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartassessment.StudentActivity,
      generated.grpc.smartassessment.MonitoringAlert> getLiveAssessmentMonitoringMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiveAssessmentMonitoring",
      requestType = generated.grpc.smartassessment.StudentActivity.class,
      responseType = generated.grpc.smartassessment.MonitoringAlert.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.smartassessment.StudentActivity,
      generated.grpc.smartassessment.MonitoringAlert> getLiveAssessmentMonitoringMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartassessment.StudentActivity, generated.grpc.smartassessment.MonitoringAlert> getLiveAssessmentMonitoringMethod;
    if ((getLiveAssessmentMonitoringMethod = SmartAssessmentServiceGrpc.getLiveAssessmentMonitoringMethod) == null) {
      synchronized (SmartAssessmentServiceGrpc.class) {
        if ((getLiveAssessmentMonitoringMethod = SmartAssessmentServiceGrpc.getLiveAssessmentMonitoringMethod) == null) {
          SmartAssessmentServiceGrpc.getLiveAssessmentMonitoringMethod = getLiveAssessmentMonitoringMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartassessment.StudentActivity, generated.grpc.smartassessment.MonitoringAlert>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartAssessmentGrpc.SmartAssessmentService", "LiveAssessmentMonitoring"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.StudentActivity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartassessment.MonitoringAlert.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartAssessmentServiceMethodDescriptorSupplier("LiveAssessmentMonitoring"))
                  .build();
          }
        }
     }
     return getLiveAssessmentMonitoringMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SmartAssessmentServiceStub newStub(io.grpc.Channel channel) {
    return new SmartAssessmentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartAssessmentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SmartAssessmentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SmartAssessmentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SmartAssessmentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SmartAssessmentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * Retrieves details about a specific assessment.
     * </pre>
     */
    public void getAssessmentDetails(generated.grpc.smartassessment.AssessmentRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.AssessmentInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssessmentDetailsMethod(), responseObserver);
    }

    /**
     * <pre>
     * CLIENT STREAMING
     * Streams multiple answers from a student exam submission
     * and returns a final submission summary.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartassessment.AnswerSubmission> submitAssessmentAnswers(
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.SubmissionSummary> responseObserver) {
      return asyncUnimplementedStreamingCall(getSubmitAssessmentAnswersMethod(), responseObserver);
    }

    /**
     * <pre>
     * SERVER STREAMING
     * Streams assessment results for administrative review.
     * </pre>
     */
    public void streamAssessmentResults(generated.grpc.smartassessment.ResultRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.StudentResult> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamAssessmentResultsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BIDIRECTIONAL STREAMING
     * Enables real-time exam monitoring and alert responses.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartassessment.StudentActivity> liveAssessmentMonitoring(
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.MonitoringAlert> responseObserver) {
      return asyncUnimplementedStreamingCall(getLiveAssessmentMonitoringMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAssessmentDetailsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.smartassessment.AssessmentRequest,
                generated.grpc.smartassessment.AssessmentInfo>(
                  this, METHODID_GET_ASSESSMENT_DETAILS)))
          .addMethod(
            getSubmitAssessmentAnswersMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.smartassessment.AnswerSubmission,
                generated.grpc.smartassessment.SubmissionSummary>(
                  this, METHODID_SUBMIT_ASSESSMENT_ANSWERS)))
          .addMethod(
            getStreamAssessmentResultsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                generated.grpc.smartassessment.ResultRequest,
                generated.grpc.smartassessment.StudentResult>(
                  this, METHODID_STREAM_ASSESSMENT_RESULTS)))
          .addMethod(
            getLiveAssessmentMonitoringMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                generated.grpc.smartassessment.StudentActivity,
                generated.grpc.smartassessment.MonitoringAlert>(
                  this, METHODID_LIVE_ASSESSMENT_MONITORING)))
          .build();
    }
  }

  /**
   */
  public static final class SmartAssessmentServiceStub extends io.grpc.stub.AbstractStub<SmartAssessmentServiceStub> {
    private SmartAssessmentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartAssessmentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartAssessmentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartAssessmentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * Retrieves details about a specific assessment.
     * </pre>
     */
    public void getAssessmentDetails(generated.grpc.smartassessment.AssessmentRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.AssessmentInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssessmentDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CLIENT STREAMING
     * Streams multiple answers from a student exam submission
     * and returns a final submission summary.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartassessment.AnswerSubmission> submitAssessmentAnswers(
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.SubmissionSummary> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSubmitAssessmentAnswersMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * SERVER STREAMING
     * Streams assessment results for administrative review.
     * </pre>
     */
    public void streamAssessmentResults(generated.grpc.smartassessment.ResultRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.StudentResult> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamAssessmentResultsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BIDIRECTIONAL STREAMING
     * Enables real-time exam monitoring and alert responses.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartassessment.StudentActivity> liveAssessmentMonitoring(
        io.grpc.stub.StreamObserver<generated.grpc.smartassessment.MonitoringAlert> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getLiveAssessmentMonitoringMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SmartAssessmentServiceBlockingStub extends io.grpc.stub.AbstractStub<SmartAssessmentServiceBlockingStub> {
    private SmartAssessmentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartAssessmentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartAssessmentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartAssessmentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * Retrieves details about a specific assessment.
     * </pre>
     */
    public generated.grpc.smartassessment.AssessmentInfo getAssessmentDetails(generated.grpc.smartassessment.AssessmentRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssessmentDetailsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SERVER STREAMING
     * Streams assessment results for administrative review.
     * </pre>
     */
    public java.util.Iterator<generated.grpc.smartassessment.StudentResult> streamAssessmentResults(
        generated.grpc.smartassessment.ResultRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamAssessmentResultsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SmartAssessmentServiceFutureStub extends io.grpc.stub.AbstractStub<SmartAssessmentServiceFutureStub> {
    private SmartAssessmentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartAssessmentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartAssessmentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartAssessmentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY (SIMPLE RPC)
     * Retrieves details about a specific assessment.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.smartassessment.AssessmentInfo> getAssessmentDetails(
        generated.grpc.smartassessment.AssessmentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssessmentDetailsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ASSESSMENT_DETAILS = 0;
  private static final int METHODID_STREAM_ASSESSMENT_RESULTS = 1;
  private static final int METHODID_SUBMIT_ASSESSMENT_ANSWERS = 2;
  private static final int METHODID_LIVE_ASSESSMENT_MONITORING = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SmartAssessmentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SmartAssessmentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ASSESSMENT_DETAILS:
          serviceImpl.getAssessmentDetails((generated.grpc.smartassessment.AssessmentRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.smartassessment.AssessmentInfo>) responseObserver);
          break;
        case METHODID_STREAM_ASSESSMENT_RESULTS:
          serviceImpl.streamAssessmentResults((generated.grpc.smartassessment.ResultRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.smartassessment.StudentResult>) responseObserver);
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
        case METHODID_SUBMIT_ASSESSMENT_ANSWERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.submitAssessmentAnswers(
              (io.grpc.stub.StreamObserver<generated.grpc.smartassessment.SubmissionSummary>) responseObserver);
        case METHODID_LIVE_ASSESSMENT_MONITORING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.liveAssessmentMonitoring(
              (io.grpc.stub.StreamObserver<generated.grpc.smartassessment.MonitoringAlert>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SmartAssessmentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartAssessmentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.smartassessment.SmartAssessmentServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartAssessmentService");
    }
  }

  private static final class SmartAssessmentServiceFileDescriptorSupplier
      extends SmartAssessmentServiceBaseDescriptorSupplier {
    SmartAssessmentServiceFileDescriptorSupplier() {}
  }

  private static final class SmartAssessmentServiceMethodDescriptorSupplier
      extends SmartAssessmentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmartAssessmentServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SmartAssessmentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SmartAssessmentServiceFileDescriptorSupplier())
              .addMethod(getGetAssessmentDetailsMethod())
              .addMethod(getSubmitAssessmentAnswersMethod())
              .addMethod(getStreamAssessmentResultsMethod())
              .addMethod(getLiveAssessmentMonitoringMethod())
              .build();
        }
      }
    }
    return result;
  }
}
