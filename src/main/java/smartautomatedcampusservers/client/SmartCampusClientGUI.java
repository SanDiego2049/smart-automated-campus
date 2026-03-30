/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartautomatedcampusservers.client;

/**
 *
 * @author Penelope
 */
import generated.grpc.smartassessment.*;
import generated.grpc.smartclassroom.*;
import generated.grpc.smartlearningresource.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import smartautomatedcampusservers.jmDNS.ServiceDiscovery;


 //client GUI for Smart Automated Campus discovers and interacts with three gRPC

public class SmartCampusClientGUI extends JFrame {

    // service discovery timeout
    private static long discoveryTimeout = 10000;

    // service types for discovery
    private static String classroomServiceType = "_smartclassroom._tcp.local.";
    private static String resourceServiceType = "_smartlearningresource._tcp.local.";
    private static String assessmentServiceType = "_smartassessment._tcp.local.";

    // service names
    private static String classroomServiceName = "SmartClassroomService";
    private static String resourceServiceName = "SmartLearningResourceService";
    private static String assessmentServiceName = "SmartAssessmentService";

    // gRPC channels and stubs
    private ManagedChannel classroomChannel;
    private ManagedChannel resourceChannel;
    private ManagedChannel assessmentChannel;

    private SmartClassroomServiceGrpc.SmartClassroomServiceStub classroomStub;
    private SmartLearningResourceServiceGrpc.SmartLearningResourceServiceBlockingStub resourceBlockingStub;
    private SmartLearningResourceServiceGrpc.SmartLearningResourceServiceStub resourceStub;
    private SmartAssessmentServiceGrpc.SmartAssessmentServiceBlockingStub assessmentBlockingStub;
    private SmartAssessmentServiceGrpc.SmartAssessmentServiceStub assessmentStub;

    // GUI Components
    private JTextArea outputArea;
    private JTabbedPane tabbedPane;

    public SmartCampusClientGUI() {
        setTitle("Smart Automated Campus - Client Control Panel");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //output area at the bottom
        outputArea = new JTextArea(10, 80);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Service Output"));
        add(scrollPane, BorderLayout.SOUTH);

        // tabbed pane for different services
        tabbedPane = new JTabbedPane();

        //add service panels
        tabbedPane.addTab("Smart Classroom", createClassroomPanel());
        tabbedPane.addTab("Learning Resources", createResourcePanel());
        tabbedPane.addTab("Assessment", createAssessmentPanel());

        add(tabbedPane, BorderLayout.CENTER);

        // Discover services on startup
        discoverServices();
    }

    
     // Smart Classroom service panel
     
    private JPanel createClassroomPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton uploadAttendanceBtn = new JButton("Upload Attendance Records (Client Streaming)");
        uploadAttendanceBtn.addActionListener(e -> uploadAttendanceRecords());
        panel.add(uploadAttendanceBtn);

        JButton liveClassBtn = new JButton("Start Live Class Session (Bidirectional Streaming)");
        liveClassBtn.addActionListener(e -> startLiveClassInteraction());
        panel.add(liveClassBtn);

        return panel;
    }

    
     //Learning Resource service panel
     
    private JPanel createResourcePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel checkPanel = new JPanel(new FlowLayout());
        JTextField resourceIdField = new JTextField("RES001", 15);
        JButton checkBtn = new JButton("Check Resource Availability (Unary)");
        checkBtn.addActionListener(e -> checkResourceAvailability(resourceIdField.getText()));
        checkPanel.add(new JLabel("Resource ID:"));
        checkPanel.add(resourceIdField);
        checkPanel.add(checkBtn);
        panel.add(checkPanel);

        JPanel streamPanel = new JPanel(new FlowLayout());
        JTextField categoryField = new JTextField("eBook", 15);
        JButton streamBtn = new JButton("Stream Resources (Server Streaming)");
        streamBtn.addActionListener(e -> streamAvailableResources(categoryField.getText()));
        streamPanel.add(new JLabel("Category:"));
        streamPanel.add(categoryField);
        streamPanel.add(streamBtn);
        panel.add(streamPanel);

        return panel;
    }

    
     // Assessment service panel
     
    private JPanel createAssessmentPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel detailsPanel = new JPanel(new FlowLayout());
        JTextField assessmentIdField = new JTextField("ASSESS001", 15);
        JButton detailsBtn = new JButton("Get Assessment Details (Unary)");
        detailsBtn.addActionListener(e -> getAssessmentDetails(assessmentIdField.getText()));
        detailsPanel.add(new JLabel("Assessment ID:"));
        detailsPanel.add(assessmentIdField);
        detailsPanel.add(detailsBtn);
        panel.add(detailsPanel);

        JButton submitBtn = new JButton("Submit Assessment Answers (Client Streaming)");
        submitBtn.addActionListener(e -> submitAssessmentAnswers());
        panel.add(submitBtn);

        JPanel resultsPanel = new JPanel(new FlowLayout());
        JTextField resultsIdField = new JTextField("ASSESS001", 15);
        JButton resultsBtn = new JButton("Stream Assessment Results (Server Streaming)");
        resultsBtn.addActionListener(e -> streamAssessmentResults(resultsIdField.getText()));
        resultsPanel.add(new JLabel("Assessment ID:"));
        resultsPanel.add(resultsIdField);
        resultsPanel.add(resultsBtn);
        panel.add(resultsPanel);

        JButton monitorBtn = new JButton("Start Live Assessment Monitoring (Bidirectional)");
        monitorBtn.addActionListener(e -> startLiveMonitoring());
        panel.add(monitorBtn);

        return panel;
    }

    
    //Discover all three gRPC services using jmDNS
     
    private void discoverServices() {
        appendOutput("\n=== Starting Service Discovery ===");

        try {
            // discover SmartClassroom Service
            appendOutput("Discovering SmartClassroom Service...");
            ServiceDiscovery classroomDiscovery = new ServiceDiscovery(classroomServiceType, classroomServiceName);
            classroomDiscovery.discoverService(discoveryTimeout);

            if (classroomDiscovery.getPort() != -1) {
                String address = classroomDiscovery.getGrpcAddress();
                appendOutput("SmartClassroom Service found at: " + address);

                classroomChannel = ManagedChannelBuilder.forTarget(address).usePlaintext().build();

                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER), "campus-client-001");
                classroomStub = MetadataUtils.attachHeaders(SmartClassroomServiceGrpc.newStub(classroomChannel), metadata);
            } else {
                appendOutput("ERROR: SmartClassroom Service not found!");
            }
            classroomDiscovery.close();

            //Discover SmartLearningResource Service
            appendOutput("Discovering SmartLearningResource Service...");
            ServiceDiscovery resourceDiscovery = new ServiceDiscovery(resourceServiceType, resourceServiceName);
            resourceDiscovery.discoverService(discoveryTimeout);

            if (resourceDiscovery.getPort() != -1) {
                String address = resourceDiscovery.getGrpcAddress();
                appendOutput("SmartLearningResource Service found at: " + address);

                resourceChannel = ManagedChannelBuilder.forTarget(address).usePlaintext().build();

                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER), "campus-client-001");
                resourceBlockingStub = MetadataUtils.attachHeaders(SmartLearningResourceServiceGrpc.newBlockingStub(resourceChannel), metadata);
                resourceStub = MetadataUtils.attachHeaders(SmartLearningResourceServiceGrpc.newStub(resourceChannel), metadata);
            } else {
                appendOutput("ERROR: SmartLearningResource Service not found!");
            }
            resourceDiscovery.close();

            //fiscover SmartAssessment Service
            appendOutput("Discovering SmartAssessment Service...");
            ServiceDiscovery assessmentDiscovery = new ServiceDiscovery(assessmentServiceType, assessmentServiceName);
            assessmentDiscovery.discoverService(discoveryTimeout);

            if (assessmentDiscovery.getPort() != -1) {
                String address = assessmentDiscovery.getGrpcAddress();
                appendOutput("SmartAssessment Service found at: " + address);

                assessmentChannel = ManagedChannelBuilder.forTarget(address).usePlaintext().build();

                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER), "campus-client-001");
                assessmentBlockingStub = MetadataUtils.attachHeaders(SmartAssessmentServiceGrpc.newBlockingStub(assessmentChannel), metadata);
                assessmentStub = MetadataUtils.attachHeaders(SmartAssessmentServiceGrpc.newStub(assessmentChannel), metadata);
            } else {
                appendOutput("ERROR: SmartAssessment Service not found!");
            }
            assessmentDiscovery.close();

            appendOutput("=== Service Discovery Completed ===\n");

        } catch (InterruptedException | IOException e) {
            appendOutput("ERROR during service discovery: " + e.getMessage());
        }
    }

    
    //Upload Attendance Records
    
    private void uploadAttendanceRecords() {
        appendOutput("\n--- Uploading Attendance Records (Client Streaming) ---");

        StreamObserver<AttendanceSummary> responseObserver = new StreamObserver<AttendanceSummary>() {
            @Override
            public void onNext(AttendanceSummary summary) {
                appendOutput("Attendance Summary Received:");
                appendOutput("Total Students Marked: " + summary.getTotalStudentsMarked());
            }

            @Override
            public void onError(Throwable t) {
                appendOutput("ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                appendOutput("Attendance upload completed successfully!");
            }
        };

        StreamObserver<AttendanceRecord> requestObserver = classroomStub.uploadAttendanceRecords(responseObserver);

        try {
            String[] studentIds = {"STU001", "STU002", "STU003", "STU004", "STU005"};
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (String studentId : studentIds) {
                AttendanceRecord record = AttendanceRecord.newBuilder()
                        .setStudentId(studentId)
                        .setTimestamp(sdf.format(new Date()))
                        .build();
                requestObserver.onNext(record);
                appendOutput("Sent: " + studentId + " at " + record.getTimestamp());
                Thread.sleep(200);
            }
            requestObserver.onCompleted();

        } catch (Exception e) {
            appendOutput("ERROR: " + e.getMessage());
            requestObserver.onError(e);
        }
    }

    
    // Live Class Interaction
     
    private void startLiveClassInteraction() {
        appendOutput("\n--- Starting Live Class Session (Bidirectional Streaming) ---");

        StreamObserver<LecturerReply> responseObserver = new StreamObserver<LecturerReply>() {
            @Override
            public void onNext(LecturerReply reply) {
                appendOutput("Lecturer Reply: " + reply.getResponseText());
            }

            @Override
            public void onError(Throwable t) {
                appendOutput("ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                appendOutput("Live class session ended!");
            }
        };

        StreamObserver<StudentQuestion> requestObserver = classroomStub.liveClassInteraction(responseObserver);

        try {
            String[][] questions = {
                {"STU001", "Can you explain gRPC streaming?"},
                {"STU002", "What is the difference between unary and streaming RPC?"},
                {"STU003", "How does service discovery work with jmDNS?"}
            };

            for (String[] question : questions) {
                StudentQuestion studentQuestion = StudentQuestion.newBuilder()
                        .setStudentId(question[0])
                        .setQuestionText(question[1])
                        .build();
                requestObserver.onNext(studentQuestion);
                appendOutput("Student " + question[0] + " asked: " + question[1]);
                Thread.sleep(1000);
            }
            requestObserver.onCompleted();

        } catch (Exception e) {
            appendOutput("ERROR: " + e.getMessage());
            requestObserver.onError(e);
        }
    }

    
    //Check Resource Availability
    
    private void checkResourceAvailability(String resourceId) {
        appendOutput("\n--- Checking Resource Availability (Unary) ---");
        appendOutput("Resource ID: " + resourceId);

        try {
            ResourceRequest request = ResourceRequest.newBuilder().setResourceId(resourceId).build();
            ResourceStatus status = resourceBlockingStub.getResourceAvailability(request);

            appendOutput("Resource Status:");
            appendOutput("  Available: " + status.getAvailable());
            appendOutput("  Location: " + status.getLocation());
        } catch (Exception e) {
            appendOutput("ERROR: " + e.getMessage());
        }
    }

   
    //Stream Available Resources
     
    private void streamAvailableResources(String category) {
        appendOutput("\n--- Streaming Available Resources (Server Streaming) ---");
        appendOutput("Category: " + category);

        CategoryRequest request = CategoryRequest.newBuilder().setCategory(category).build();

        StreamObserver<ResourceInfo> responseObserver = new StreamObserver<ResourceInfo>() {
            int count = 0;

            @Override
            public void onNext(ResourceInfo info) {
                count++;
                appendOutput("Resource " + count + ":");
                appendOutput("  Title: " + info.getTitle());
                appendOutput("  Author: " + info.getAuthor());
                appendOutput("  Format: " + info.getFormat());
            }

            @Override
            public void onError(Throwable t) {
                appendOutput("ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                appendOutput("Streaming completed. Total resources: " + count);
            }
        };

        resourceStub.streamAvailableResources(request, responseObserver);
    }

    
    //Get Assessment Details
     
    private void getAssessmentDetails(String assessmentId) {
        appendOutput("\n--- Getting Assessment Details (Unary) ---");
        appendOutput("Assessment ID: " + assessmentId);

        try {
            AssessmentRequest request = AssessmentRequest.newBuilder().setAssessmentId(assessmentId).build();
            AssessmentInfo info = assessmentBlockingStub.getAssessmentDetails(request);

            appendOutput("Assessment Information:");
            appendOutput("  Subject: " + info.getSubject());
            appendOutput("  Duration: " + info.getDurationMinutes() + " minutes");
        } catch (Exception e) {
            appendOutput("ERROR: " + e.getMessage());
        }
    }

    
    // Submit Assessment Answers
    
    private void submitAssessmentAnswers() {
        appendOutput("\n--- Submitting Assessment Answers (Client Streaming) ---");

        StreamObserver<SubmissionSummary> responseObserver = new StreamObserver<SubmissionSummary>() {
            @Override
            public void onNext(SubmissionSummary summary) {
                appendOutput("Submission Summary Received:");
                appendOutput("Total Score: " + String.format("%.2f", summary.getTotalScore()));
            }

            @Override
            public void onError(Throwable t) {
                appendOutput("ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                appendOutput("Answer submission completed successfully!");
            }
        };

        StreamObserver<AnswerSubmission> requestObserver = assessmentStub.submitAssessmentAnswers(responseObserver);

        try {
            String[][] answers = {
                {"Q1", "gRPC is a high-performance RPC framework"},
                {"Q2", "It uses Protocol Buffers for serialization"},
                {"Q3", "Supports multiple streaming modes"},
                {"Q4", "jmDNS enables service discovery"},
                {"Q5", "Metadata can be used for authentication"}
            };

            for (String[] answer : answers) {
                AnswerSubmission submission = AnswerSubmission.newBuilder()
                        .setQuestionId(answer[0])
                        .setAnswerText(answer[1])
                        .build();
                requestObserver.onNext(submission);
                appendOutput("Submitted: " + answer[0] + " - " + answer[1]);
                Thread.sleep(200);
            }
            requestObserver.onCompleted();

        } catch (Exception e) {
            appendOutput("ERROR: " + e.getMessage());
            requestObserver.onError(e);
        }
    }

   
    // Stream Assessment Results
     
    private void streamAssessmentResults(String assessmentId) {
        appendOutput("\n--- Streaming Assessment Results (Server Streaming) ---");
        appendOutput("Assessment ID: " + assessmentId);

        ResultRequest request = ResultRequest.newBuilder().setAssessmentId(assessmentId).build();

        StreamObserver<StudentResult> responseObserver = new StreamObserver<StudentResult>() {
            int count = 0;

            @Override
            public void onNext(StudentResult result) {
                count++;
                appendOutput("Student " + result.getStudentId() + ": Score = " + result.getScore());
            }

            @Override
            public void onError(Throwable t) {
                appendOutput("ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                appendOutput("Streaming completed. Total results: " + count);
            }
        };

        assessmentStub.streamAssessmentResults(request, responseObserver);
    }

    
    // Live Assessment Monitoring
     
    private void startLiveMonitoring() {
        appendOutput("\n--- Starting Live Assessment Monitoring (Bidirectional Streaming) ---");

        StreamObserver<MonitoringAlert> responseObserver = new StreamObserver<MonitoringAlert>() {
            @Override
            public void onNext(MonitoringAlert alert) {
                appendOutput("Alert: " + alert.getAlertMessage());
            }

            @Override
            public void onError(Throwable t) {
                appendOutput("ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                appendOutput("Monitoring session ended!");
            }
        };

        StreamObserver<StudentActivity> requestObserver = assessmentStub.liveAssessmentMonitoring(responseObserver);

        try {
            String[][] activities = {
                {"normal_activity", getCurrentTimestamp()},
                {"tab_switch", getCurrentTimestamp()},
                {"normal_activity", getCurrentTimestamp()},
                {"suspicious_activity", getCurrentTimestamp()}
            };

            for (String[] activity : activities) {
                StudentActivity studentActivity = StudentActivity.newBuilder().setActivityType(activity[0]).setTimestamp(activity[1]).build();
                requestObserver.onNext(studentActivity);
                appendOutput("Logged activity: " + activity[0] + " at " + activity[1]);
                Thread.sleep(1000);
            }
            requestObserver.onCompleted();

        } catch (Exception e) {
            appendOutput("ERROR: " + e.getMessage());
            requestObserver.onError(e);
        }
    }

    
     //append text to output area
    
    private void appendOutput(String text) {
        SwingUtilities.invokeLater(() -> {
            outputArea.append(text + "\n");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });
    }

    
     //Get current timestamp
    
    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    
    // shutdown channels on exit
     
    private void shutdown() {
        if (classroomChannel != null) {
            classroomChannel.shutdown();
        }
        if (resourceChannel != null) {
            resourceChannel.shutdown();
        }
        if (assessmentChannel != null) {
            assessmentChannel.shutdown();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartCampusClientGUI gui = new SmartCampusClientGUI();
            gui.setVisible(true);

            //aadd shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(gui::shutdown));
        });
    }
}
