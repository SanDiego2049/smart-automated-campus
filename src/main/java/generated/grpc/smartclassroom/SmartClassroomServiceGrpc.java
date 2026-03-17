package generated.grpc.smartclassroom;

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
    comments = "Source: smart_classroom.proto")
public final class SmartClassroomServiceGrpc {

  private SmartClassroomServiceGrpc() {}

  public static final String SERVICE_NAME = "SmartClassroomGrpc.SmartClassroomService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartclassroom.AttendanceRecord,
      generated.grpc.smartclassroom.AttendanceSummary> getUploadAttendanceRecordsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadAttendanceRecords",
      requestType = generated.grpc.smartclassroom.AttendanceRecord.class,
      responseType = generated.grpc.smartclassroom.AttendanceSummary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.smartclassroom.AttendanceRecord,
      generated.grpc.smartclassroom.AttendanceSummary> getUploadAttendanceRecordsMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartclassroom.AttendanceRecord, generated.grpc.smartclassroom.AttendanceSummary> getUploadAttendanceRecordsMethod;
    if ((getUploadAttendanceRecordsMethod = SmartClassroomServiceGrpc.getUploadAttendanceRecordsMethod) == null) {
      synchronized (SmartClassroomServiceGrpc.class) {
        if ((getUploadAttendanceRecordsMethod = SmartClassroomServiceGrpc.getUploadAttendanceRecordsMethod) == null) {
          SmartClassroomServiceGrpc.getUploadAttendanceRecordsMethod = getUploadAttendanceRecordsMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartclassroom.AttendanceRecord, generated.grpc.smartclassroom.AttendanceSummary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartClassroomGrpc.SmartClassroomService", "UploadAttendanceRecords"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartclassroom.AttendanceRecord.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartclassroom.AttendanceSummary.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartClassroomServiceMethodDescriptorSupplier("UploadAttendanceRecords"))
                  .build();
          }
        }
     }
     return getUploadAttendanceRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.smartclassroom.StudentQuestion,
      generated.grpc.smartclassroom.LecturerReply> getLiveClassInteractionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiveClassInteraction",
      requestType = generated.grpc.smartclassroom.StudentQuestion.class,
      responseType = generated.grpc.smartclassroom.LecturerReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.smartclassroom.StudentQuestion,
      generated.grpc.smartclassroom.LecturerReply> getLiveClassInteractionMethod() {
    io.grpc.MethodDescriptor<generated.grpc.smartclassroom.StudentQuestion, generated.grpc.smartclassroom.LecturerReply> getLiveClassInteractionMethod;
    if ((getLiveClassInteractionMethod = SmartClassroomServiceGrpc.getLiveClassInteractionMethod) == null) {
      synchronized (SmartClassroomServiceGrpc.class) {
        if ((getLiveClassInteractionMethod = SmartClassroomServiceGrpc.getLiveClassInteractionMethod) == null) {
          SmartClassroomServiceGrpc.getLiveClassInteractionMethod = getLiveClassInteractionMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.smartclassroom.StudentQuestion, generated.grpc.smartclassroom.LecturerReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartClassroomGrpc.SmartClassroomService", "LiveClassInteraction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartclassroom.StudentQuestion.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.smartclassroom.LecturerReply.getDefaultInstance()))
                  .setSchemaDescriptor(new SmartClassroomServiceMethodDescriptorSupplier("LiveClassInteraction"))
                  .build();
          }
        }
     }
     return getLiveClassInteractionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SmartClassroomServiceStub newStub(io.grpc.Channel channel) {
    return new SmartClassroomServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartClassroomServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SmartClassroomServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SmartClassroomServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SmartClassroomServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SmartClassroomServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CLIENT STREAMING
     * Streams multiple attendance records from a classroom scanner and returns a summary once all students have been marked present.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.AttendanceRecord> uploadAttendanceRecords(
        io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.AttendanceSummary> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadAttendanceRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BIDIRECTIONAL STREAMING
     * Enables real-time communication between students and lecturer during a live class session.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.StudentQuestion> liveClassInteraction(
        io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.LecturerReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getLiveClassInteractionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadAttendanceRecordsMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.smartclassroom.AttendanceRecord,
                generated.grpc.smartclassroom.AttendanceSummary>(
                  this, METHODID_UPLOAD_ATTENDANCE_RECORDS)))
          .addMethod(
            getLiveClassInteractionMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                generated.grpc.smartclassroom.StudentQuestion,
                generated.grpc.smartclassroom.LecturerReply>(
                  this, METHODID_LIVE_CLASS_INTERACTION)))
          .build();
    }
  }

  /**
   */
  public static final class SmartClassroomServiceStub extends io.grpc.stub.AbstractStub<SmartClassroomServiceStub> {
    private SmartClassroomServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartClassroomServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartClassroomServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartClassroomServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * CLIENT STREAMING
     * Streams multiple attendance records from a classroom scanner and returns a summary once all students have been marked present.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.AttendanceRecord> uploadAttendanceRecords(
        io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.AttendanceSummary> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadAttendanceRecordsMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * BIDIRECTIONAL STREAMING
     * Enables real-time communication between students and lecturer during a live class session.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.StudentQuestion> liveClassInteraction(
        io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.LecturerReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getLiveClassInteractionMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SmartClassroomServiceBlockingStub extends io.grpc.stub.AbstractStub<SmartClassroomServiceBlockingStub> {
    private SmartClassroomServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartClassroomServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartClassroomServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartClassroomServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class SmartClassroomServiceFutureStub extends io.grpc.stub.AbstractStub<SmartClassroomServiceFutureStub> {
    private SmartClassroomServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmartClassroomServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartClassroomServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmartClassroomServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPLOAD_ATTENDANCE_RECORDS = 0;
  private static final int METHODID_LIVE_CLASS_INTERACTION = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SmartClassroomServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SmartClassroomServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_UPLOAD_ATTENDANCE_RECORDS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadAttendanceRecords(
              (io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.AttendanceSummary>) responseObserver);
        case METHODID_LIVE_CLASS_INTERACTION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.liveClassInteraction(
              (io.grpc.stub.StreamObserver<generated.grpc.smartclassroom.LecturerReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SmartClassroomServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartClassroomServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.smartclassroom.SmartClassroomServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartClassroomService");
    }
  }

  private static final class SmartClassroomServiceFileDescriptorSupplier
      extends SmartClassroomServiceBaseDescriptorSupplier {
    SmartClassroomServiceFileDescriptorSupplier() {}
  }

  private static final class SmartClassroomServiceMethodDescriptorSupplier
      extends SmartClassroomServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmartClassroomServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SmartClassroomServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SmartClassroomServiceFileDescriptorSupplier())
              .addMethod(getUploadAttendanceRecordsMethod())
              .addMethod(getLiveClassInteractionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
