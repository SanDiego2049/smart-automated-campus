/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartautomatedcampusservers.servers;

/**
 *
 * @author Penelope
 */
import generated.grpc.smartassessment.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.*;
import smartautomatedcampusservers.jmDNS.ServiceRegistration;

public class SmartAssessmentServer extends SmartAssessmentServiceGrpc.SmartAssessmentServiceImplBase {

    private static int port = 50053;
    private static String serviceType = "_smartassessment._tcp.local.";
    private static String serviceName = "SmartAssessmentService";

    //metadata keys for simple metadata demonstration
    private static Metadata.Key<String> clientIdKey = Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER);

    // simulated assessment database using ArrayList
    private static List<AssessmentData> assessmentDatabase = new ArrayList<>();
    private static List<StudentScore> studentScores = new ArrayList<>();

    static {
        assessmentDatabase.add(new AssessmentData("ASSESS001", "Distributed Systems", 120));
        assessmentDatabase.add(new AssessmentData("ASSESS002", "Computer Networks", 90));
        assessmentDatabase.add(new AssessmentData("ASSESS003", "Database Systems", 150));

        // populated student scores for streaming results
        studentScores.add(new StudentScore("STU001", 85.5));
        studentScores.add(new StudentScore("STU002", 92.0));
        studentScores.add(new StudentScore("STU003", 78.5));
        studentScores.add(new StudentScore("STU004", 88.0));
        studentScores.add(new StudentScore("STU005", 95.5));
    }

    public static void main(String[] args) {
        SmartAssessmentServer assessmentServer = new SmartAssessmentServer();

        try {
            // start gRPC server
            Server server = ServerBuilder.forPort(port).addService(assessmentServer).build().start();

            System.out.println("SmartAssessment Server started on port " + port);

            // register service with jmDNS using ServiceRegistration utility
            ServiceRegistration registration = ServiceRegistration.getInstance();
            registration.registerService(serviceType, serviceName, port, "Smart Assessment Management Service");

            System.out.println("SmartAssessment Service registered with jmDNS");
            System.out.println("Service Type: " + serviceType);
            System.out.println("Service Name: " + serviceName);

            // keep server running
            server.awaitTermination();

        } catch (IOException | InterruptedException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Get assessment details
    @Override
    public void getAssessmentDetails(AssessmentRequest request, StreamObserver<AssessmentInfo> responseObserver) {

        Context ctx = Context.current();

        try {
            //check for cancellation
            if (ctx.isCancelled()) {
                System.out.println("Request cancelled");
                responseObserver.onError(Status.CANCELLED.withDescription("Assessment details request cancelled").asRuntimeException());
                return;
            }

            String assessmentId = request.getAssessmentId();
            System.out.println("Fetching details for assessment: " + assessmentId);

            //validate input
            if (assessmentId == null || assessmentId.isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Assessment ID cannot be empty").asRuntimeException());
                return;
            }

            // search for assessment in ArrayList
            AssessmentData data = null;
            for (AssessmentData assessment : assessmentDatabase) {
                if (assessment.assessmentId.equals(assessmentId)) {
                    data = assessment;
                    break;
                }
            }

            if (data == null) {
                // asssessment not found
                responseObserver.onError(Status.NOT_FOUND.withDescription("Assessment ID '" + assessmentId + "' not found in database").asRuntimeException());
                return;
            }

            AssessmentInfo info = AssessmentInfo.newBuilder().setSubject(data.subject).setDurationMinutes(data.durationMinutes).build();

            responseObserver.onNext(info);
            responseObserver.onCompleted();

            System.out.println("Response sent: Subject = " + info.getSubject() + ", Duration = " + info.getDurationMinutes() + " minutes");

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error fetching assessment details: " + e.getMessage()).asRuntimeException());
        }
    }

    //Submit assessment answers
    @Override
    public StreamObserver<AnswerSubmission> submitAssessmentAnswers(
            StreamObserver<SubmissionSummary> responseObserver) {

        Context ctx = Context.current();

        return new StreamObserver<AnswerSubmission>() {
            List<AnswerSubmission> answers = new ArrayList<>();
            double totalScore = 0.0;
            boolean isCancelled = false;

            @Override
            public void onNext(AnswerSubmission answer) {
                //check for cancellation
                if (ctx.isCancelled()) {
                    isCancelled = true;
                    System.out.println("Answer submission cancelled");
                    responseObserver.onError(Status.CANCELLED.withDescription("Assessment answer submission cancelled").asRuntimeException());
                    return;
                }

                //validate input
                if (answer.getQuestionId() == null || answer.getQuestionId().isEmpty()) {
                    responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Question ID cannot be empty").asRuntimeException());
                    return;
                }

                if (answer.getAnswerText() == null || answer.getAnswerText().isEmpty()) {
                    responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Answer text cannot be empty").asRuntimeException());
                    return;
                }

                System.out.println("Received answer for Question ID: " + answer.getQuestionId() + ", Answer: " + answer.getAnswerText());
                answers.add(answer);

                //simulate scoring
                double questionScore = Math.random() * 10;
                totalScore += questionScore;
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in answer submission: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                if (isCancelled) {
                    return;
                }

                try {
                    if (answers.isEmpty()) {
                        responseObserver.onError(Status.FAILED_PRECONDITION.withDescription("No answers were submitted").asRuntimeException());
                        return;
                    }

                    System.out.println("Assessment submission completed. Total answers: " + answers.size());
                    System.out.println("Calculated total score: " + totalScore);

                    SubmissionSummary summary = SubmissionSummary.newBuilder().setTotalScore(totalScore).build();

                    responseObserver.onNext(summary);
                    responseObserver.onCompleted();

                } catch (Exception e) {
                    responseObserver.onError(Status.INTERNAL.withDescription("Error processing submission: " + e.getMessage()).asRuntimeException());
                }
            }
        };
    }

    //  Stream assessment results
    @Override
    public void streamAssessmentResults(ResultRequest request, StreamObserver<StudentResult> responseObserver) {

        Context ctx = Context.current();

        try {
            String assessmentId = request.getAssessmentId();
            System.out.println("Streaming results for assessment: " + assessmentId);

            //validate input
            if (assessmentId == null || assessmentId.isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Assessment ID cannot be empty").asRuntimeException());
                return;
            }

            //check if assessment exists in ArrayList
            boolean assessmentExists = false;
            for (AssessmentData assessment : assessmentDatabase) {
                if (assessment.assessmentId.equals(assessmentId)) {
                    assessmentExists = true;
                    break;
                }
            }

            if (!assessmentExists) {
                responseObserver.onError(Status.NOT_FOUND.withDescription("Assessment ID '" + assessmentId + "' not found").asRuntimeException());
                return;
            }

            int count = 0;
            // loop through ArrayList to stream student scores
            for (StudentScore score : studentScores) {

                //check for cancellation before each stream
                if (ctx.isCancelled()) {
                    System.out.println("Results streaming cancelled");
                    responseObserver.onError(Status.CANCELLED.withDescription("Assessment results streaming cancelled").asRuntimeException());
                    return;
                }

                StudentResult result = StudentResult.newBuilder().setStudentId(score.studentId).setScore(score.score).build();

                responseObserver.onNext(result);
                count++;
                System.out.println("Streamed result: Student " + score.studentId + ", Score: " + score.score);

                //simulate streaming delay with deadline awareness
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    responseObserver.onError(Status.ABORTED.withDescription("Results streaming interrupted").asRuntimeException());
                    return;
                }
            }

            responseObserver.onCompleted();
            System.out.println("Streaming completed. Total results sent: " + count);

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error streaming results: " + e.getMessage()).asRuntimeException());
        }
    }

    // Live assessment monitoring
    @Override
    public StreamObserver<StudentActivity> liveAssessmentMonitoring(
            StreamObserver<MonitoringAlert> responseObserver) {

        Context ctx = Context.current();

        return new StreamObserver<StudentActivity>() {
            boolean isCancelled = false;

            @Override
            public void onNext(StudentActivity activity) {
                //check for cancellation
                if (ctx.isCancelled()) {
                    isCancelled = true;
                    System.out.println("Monitoring session cancelled");
                    responseObserver.onError(Status.CANCELLED.withDescription("Assessment monitoring session cancelled").asRuntimeException());
                    return;
                }

                //validate input
                if (activity.getActivityType() == null || activity.getActivityType().isEmpty()) {
                    MonitoringAlert errorAlert = MonitoringAlert.newBuilder().setAlertMessage("ERROR: Activity type cannot be empty").build();
                    responseObserver.onNext(errorAlert);
                    return;
                }

                System.out.println("Monitoring activity: " + activity.getActivityType() + " at " + activity.getTimestamp());

                try {
                    // simulate processing delay
                    Thread.sleep(100);

                    // simulate alert generation based on activity type
                    String alertMessage = "";

                    if (activity.getActivityType().toLowerCase().contains("suspicious")) {
                        alertMessage = "ALERT: Suspicious activity detected at " + activity.getTimestamp();
                    } else if (activity.getActivityType().toLowerCase().contains("switch")) {
                        alertMessage = "WARNING: Tab switching detected at " + activity.getTimestamp();
                    } else {
                        alertMessage = "INFO: Normal activity logged at " + activity.getTimestamp();
                    }

                    MonitoringAlert alert = MonitoringAlert.newBuilder().setAlertMessage(alertMessage).build();

                    responseObserver.onNext(alert);

                } catch (InterruptedException e) {
                    responseObserver.onError(Status.ABORTED.withDescription("Monitoring interrupted").asRuntimeException());
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in assessment monitoring: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                if (!isCancelled) {
                    System.out.println("Assessment monitoring session ended normally");
                    responseObserver.onCompleted();
                }
            }
        };
    }

    // class to store assessment data
    private static class AssessmentData {

        String assessmentId;
        String subject;
        int durationMinutes;

        AssessmentData(String assessmentId, String subject, int durationMinutes) {
            this.assessmentId = assessmentId;
            this.subject = subject;
            this.durationMinutes = durationMinutes;
        }
    }

    // class to store student scores
    private static class StudentScore {

        String studentId;
        double score;

        StudentScore(String studentId, double score) {
            this.studentId = studentId;
            this.score = score;
        }
    }
}
