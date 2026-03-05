/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package smartautomatedcampusservers;

/**
 *
 * @author Penelope
 */
import generated.grpc.smartclassroom.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class SmartClassroomServer extends SmartClassroomServiceGrpc.SmartClassroomServiceImplBase {

    private static final int PORT = 50051;
    private static final String SERVICE_TYPE = "_smartclassroom._tcp.local.";
    private static final String SERVICE_NAME = "SmartClassroomService";

    public static void main(String[] args) {
        SmartClassroomServer classroomServer = new SmartClassroomServer();

        try {
            // Start gRPC server
            Server server = ServerBuilder.forPort(PORT).addService(classroomServer).build().start();

            System.out.println("SmartClassroom Server started on port " + PORT);

            // Register service with jmDNS
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = ServiceInfo.create(SERVICE_TYPE, SERVICE_NAME, PORT, "Smart Classroom Management Service");
            jmdns.registerService(serviceInfo);

            System.out.println("SmartClassroom Service registered with jmDNS");
            System.out.println("Service Type: " + SERVICE_TYPE);
            System.out.println("Service Name: " + SERVICE_NAME);

            // Keep server running
            server.awaitTermination();

        } catch (IOException | InterruptedException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // CLIENT STREAMING: Upload attendance records
    @Override
    public StreamObserver<AttendanceRecord> uploadAttendanceRecords(
            StreamObserver<AttendanceSummary> responseObserver) {

        return new StreamObserver<AttendanceRecord>() {
            List<AttendanceRecord> attendanceList = new ArrayList<>();

            @Override
            public void onNext(AttendanceRecord record) {
                System.out.println("Received attendance: Student ID = " + record.getStudentId() + ", Timestamp = " + record.getTimestamp());
                attendanceList.add(record);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in attendance upload: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                int totalStudents = attendanceList.size();
                System.out.println("Attendance upload completed. Total students marked: " + totalStudents);

                AttendanceSummary summary = AttendanceSummary.newBuilder().setTotalStudentsMarked(totalStudents).build();

                responseObserver.onNext(summary);
                responseObserver.onCompleted();
            }
        };
    }

    // BIDIRECTIONAL STREAMING: Live class interaction
    @Override
    public StreamObserver<StudentQuestion> liveClassInteraction(
            StreamObserver<LecturerReply> responseObserver) {

        return new StreamObserver<StudentQuestion>() {

            @Override
            public void onNext(StudentQuestion question) {
                System.out.println("Question from Student " + question.getStudentId() + ": " + question.getQuestionText());

                // Simulate lecturer response
                String replyText = "Thank you for your question, Student " + question.getStudentId() + ". Let me address: " + question.getQuestionText();

                LecturerReply reply = LecturerReply.newBuilder().setResponseText(replyText).build();

                responseObserver.onNext(reply);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in live class interaction: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Live class session ended");
                responseObserver.onCompleted();
            }
        };
    }
}
