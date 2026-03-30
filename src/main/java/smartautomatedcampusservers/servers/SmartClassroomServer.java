/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package smartautomatedcampusservers.servers;

/**
 *
 * @author Penelope
 */
import generated.grpc.smartclassroom.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import smartautomatedcampusservers.jmDNS.ServiceRegistration;

public class SmartClassroomServer extends SmartClassroomServiceGrpc.SmartClassroomServiceImplBase {

    private static  int port = 50051;
    private static  String serviceType = "_smartclassroom._tcp.local.";
    private static  String serviceName = "SmartClassroomService";

    // metadata keys for simple metadata demonstration
    private static Metadata.Key<String> clientIdKey = Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER);

    public static void main(String[] args) {
        SmartClassroomServer classroomServer = new SmartClassroomServer();

        try {
            // start gRPC server
            Server server = ServerBuilder.forPort(port).addService(classroomServer).build().start();

            System.out.println("SmartClassroom Server started on port " + port);

            // register service with jmDNS using ServiceRegistration 
            ServiceRegistration registration = ServiceRegistration.getInstance();
            registration.registerService(serviceType, serviceName, port, "Smart Classroom Management Service");

            System.out.println("SmartClassroom Service registered with jmDNS");
            System.out.println("Service Type: " + serviceType);
            System.out.println("Service Name: " + serviceName);

            // keep server running
            server.awaitTermination();

        } catch (IOException | InterruptedException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Upload attendance records
    @Override
    public StreamObserver<AttendanceRecord> uploadAttendanceRecords(StreamObserver<AttendanceSummary> responseObserver) {

        Context ctx = Context.current();

        return new StreamObserver<AttendanceRecord>() {
            List<AttendanceRecord> attendanceList = new ArrayList<>();
            boolean isCancelled = false;

            @Override
            public void onNext(AttendanceRecord record) {
                // check for cancellation
                if (ctx.isCancelled()) {
                    isCancelled = true;
                    System.out.println("Request cancelled by client");
                    responseObserver.onError(Status.CANCELLED.withDescription("Attendance upload cancelled").asRuntimeException());
                    return;
                }

                //validate input
                if (record.getStudentId() == null || record.getStudentId().isEmpty()) {
                    responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Student ID cannot be empty").asRuntimeException());
                    return;
                }

                System.out.println("Received attendance: Student ID = " + record.getStudentId() + ", Timestamp = " + record.getTimestamp());
                attendanceList.add(record);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in attendance upload: " + t.getMessage());
                //wrror already handled by client
            }

            @Override
            public void onCompleted() {
                if (isCancelled) {
                    return;
                }

                try {
                    int totalStudents = attendanceList.size();

                    //simulate deadline check
                    if (totalStudents == 0) {
                        responseObserver.onError(Status.FAILED_PRECONDITION.withDescription("No attendance records received").asRuntimeException());
                        return;
                    }

                    System.out.println("Attendance upload completed. Total students marked: " + totalStudents);

                    AttendanceSummary summary = AttendanceSummary.newBuilder().setTotalStudentsMarked(totalStudents).build();

                    responseObserver.onNext(summary);
                    responseObserver.onCompleted();

                } catch (Exception e) {
                    responseObserver.onError(Status.INTERNAL.withDescription("Error processing attendance: " + e.getMessage()).asRuntimeException());
                }
            }
        };
    }

    //Live class interaction
    @Override
    public StreamObserver<StudentQuestion> liveClassInteraction(StreamObserver<LecturerReply> responseObserver) {

        Context ctx = Context.current();

        return new StreamObserver<StudentQuestion>() {
            boolean isCancelled = false;

            @Override
            public void onNext(StudentQuestion question) {
                // check for cancellation
                if (ctx.isCancelled()) {
                    isCancelled = true;
                    System.out.println("Live session cancelled");
                    responseObserver.onError(Status.CANCELLED.withDescription("Live class session cancelled").asRuntimeException());
                    return;
                }

                //validate input
                if (question.getQuestionText() == null || question.getQuestionText().isEmpty()) {
                    LecturerReply errorReply = LecturerReply.newBuilder().setResponseText("ERROR: Question text cannot be empty").build();
                    responseObserver.onNext(errorReply);
                    return;
                }

                System.out.println("Question from Student " + question.getStudentId() + ": " + question.getQuestionText());

                try {
                    // simulate processing delay
                    Thread.sleep(100);

                    // simulate lecturer response
                    String replyText = "Thank you for your question, Student " + question.getStudentId() + ". Let me address: " + question.getQuestionText();

                    LecturerReply reply = LecturerReply.newBuilder().setResponseText(replyText).build();

                    responseObserver.onNext(reply);

                } catch (InterruptedException e) {
                    responseObserver.onError(Status.ABORTED.withDescription("Response interrupted").asRuntimeException());
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in live class interaction: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                if (!isCancelled) {
                    System.out.println("Live class session ended normally");
                    responseObserver.onCompleted();
                }
            }
        };
    }
}
