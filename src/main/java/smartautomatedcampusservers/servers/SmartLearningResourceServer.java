/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartautomatedcampusservers.servers;

import generated.grpc.smartlearningresource.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import smartautomatedcampusservers.jmDNS.ServiceRegistration;

/**
 *
 * @author Penelope
 */
public class SmartLearningResourceServer extends SmartLearningResourceServiceGrpc.SmartLearningResourceServiceImplBase {

    private static final int PORT = 50052;
    private static final String SERVICE_TYPE = "_smartlearningresource._tcp.local.";
    private static final String SERVICE_NAME = "SmartLearningResourceService";

    // Metadata keys for simple metadata demonstration
    private static final Metadata.Key<String> CLIENT_ID_KEY
            = Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER);

    // Simulated resource database
    private static Map<String, ResourceData> resourceDatabase = new HashMap<>();

    static {
        resourceDatabase.put("RES001", new ResourceData(true, "Library Floor 2", "Java Programming", "James Gosling", "eBook"));
        resourceDatabase.put("RES002", new ResourceData(true, "Library Floor 3", "Distributed Systems", "Andrew Tanenbaum", "Hardcover"));
        resourceDatabase.put("RES003", new ResourceData(false, "Checked Out", "Computer Networks", "Kurose & Ross", "Paperback"));
        resourceDatabase.put("RES004", new ResourceData(true, "Digital Archive", "gRPC Essentials", "Varun Talwar", "PDF"));
        resourceDatabase.put("RES005", new ResourceData(true, "Library Floor 1", "Data Structures", "Robert Sedgewick", "Hardcover"));
    }

    public static void main(String[] args) {
        SmartLearningResourceServer resourceServer = new SmartLearningResourceServer();

        try {
            // Start gRPC server
            Server server = ServerBuilder.forPort(PORT)
                    .addService(resourceServer)
                    .build()
                    .start();

            System.out.println("SmartLearningResource Server started on port " + PORT);

            // Register service with jmDNS using ServiceRegistration utility
            ServiceRegistration registration = ServiceRegistration.getInstance();
            registration.registerService(SERVICE_TYPE, SERVICE_NAME, PORT,
                    "Smart Learning Resource Management Service");

            System.out.println("SmartLearningResource Service registered with jmDNS");
            System.out.println("Service Type: " + SERVICE_TYPE);
            System.out.println("Service Name: " + SERVICE_NAME);

            // Keep server running
            server.awaitTermination();

        } catch (IOException | InterruptedException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // UNARY: Get resource availability
    @Override
    public void getResourceAvailability(ResourceRequest request,
            StreamObserver<ResourceStatus> responseObserver) {

        Context ctx = Context.current();

        try {
            // Check for cancellation
            if (ctx.isCancelled()) {
                System.out.println("Request cancelled");
                responseObserver.onError(Status.CANCELLED
                        .withDescription("Resource availability check cancelled")
                        .asRuntimeException());
                return;
            }

            String resourceId = request.getResourceId();
            System.out.println("Checking availability for resource: " + resourceId);

            // Validate input
            if (resourceId == null || resourceId.isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Resource ID cannot be empty")
                        .asRuntimeException());
                return;
            }

            ResourceData data = resourceDatabase.get(resourceId);

            ResourceStatus.Builder statusBuilder = ResourceStatus.newBuilder();

            if (data != null) {
                statusBuilder.setAvailable(data.available)
                        .setLocation(data.location);

                System.out.println("Response sent: Available = " + data.available
                        + ", Location = " + data.location);
            } else {
                // Resource not found - return NOT_FOUND status
                responseObserver.onError(Status.NOT_FOUND
                        .withDescription("Resource ID '" + resourceId + "' not found in database")
                        .asRuntimeException());
                return;
            }

            ResourceStatus status = statusBuilder.build();
            responseObserver.onNext(status);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error checking resource availability: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    // SERVER STREAMING: Stream available resources by category
    @Override
    public void streamAvailableResources(CategoryRequest request,
            StreamObserver<ResourceInfo> responseObserver) {

        Context ctx = Context.current();

        try {
            String category = request.getCategory();
            System.out.println("Streaming resources for category: " + category);

            // Validate input
            if (category == null) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Category cannot be null")
                        .asRuntimeException());
                return;
            }

            int count = 0;
            for (Map.Entry<String, ResourceData> entry : resourceDatabase.entrySet()) {

                // Check for cancellation before each stream
                if (ctx.isCancelled()) {
                    System.out.println("Streaming cancelled by client");
                    responseObserver.onError(Status.CANCELLED
                            .withDescription("Resource streaming cancelled")
                            .asRuntimeException());
                    return;
                }

                ResourceData data = entry.getValue();

                // Filter by category (simple simulation)
                if (data.available && (category.isEmpty() || data.format.toLowerCase().contains(category.toLowerCase()))) {
                    ResourceInfo info = ResourceInfo.newBuilder()
                            .setTitle(data.title)
                            .setAuthor(data.author)
                            .setFormat(data.format)
                            .build();

                    responseObserver.onNext(info);
                    count++;
                    System.out.println("Streamed resource: " + data.title);

                    // Simulate streaming delay with deadline awareness
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        responseObserver.onError(Status.ABORTED
                                .withDescription("Streaming interrupted")
                                .asRuntimeException());
                        return;
                    }
                }
            }

            if (count == 0) {
                System.out.println("No resources found for category: " + category);
            }

            responseObserver.onCompleted();
            System.out.println("Streaming completed. Total resources sent: " + count);

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error streaming resources: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    // Helper class to store resource data
    private static class ResourceData {

        boolean available;
        String location;
        String title;
        String author;
        String format;

        ResourceData(boolean available, String location, String title, String author, String format) {
            this.available = available;
            this.location = location;
            this.title = title;
            this.author = author;
            this.format = format;
        }
    }
}
