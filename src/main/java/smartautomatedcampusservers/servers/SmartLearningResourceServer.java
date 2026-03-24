/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartautomatedcampusservers.servers;

import generated.grpc.smartlearningresource.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import smartautomatedcampusservers.jmDNS.ServiceRegistration;
 
/**
 *
 * @author Penelope
 */
public class SmartLearningResourceServer extends SmartLearningResourceServiceGrpc.SmartLearningResourceServiceImplBase {
 
    private static int port = 50052;
    private static String serviceType = "_smartlearningresource._tcp.local.";
    private static String serviceName = "SmartLearningResourceService";
    
    // Metadata keys for simple metadata demonstration
    private static Metadata.Key<String> clientIdKey = Metadata.Key.of("client-id", Metadata.ASCII_STRING_MARSHALLER);
 
    // Simulated resource database using ArrayList
    private static List<ResourceData> resourceDatabase = new ArrayList<>();
 
    static {
        resourceDatabase.add(new ResourceData("RES001", true, "Library Floor 2", "Java Programming", "James Gosling", "eBook"));
        resourceDatabase.add(new ResourceData("RES002", true, "Library Floor 3", "Distributed Systems", "Andrew Tanenbaum", "Hardcover"));
        resourceDatabase.add(new ResourceData("RES003", false, "Checked Out", "Computer Networks", "Kurose & Ross", "Paperback"));
        resourceDatabase.add(new ResourceData("RES004", true, "Digital Archive", "gRPC Essentials", "Varun Talwar", "PDF"));
        resourceDatabase.add(new ResourceData("RES005", true, "Library Floor 1", "Data Structures", "Robert Sedgewick", "Hardcover"));
    }
 
    public static void main(String[] args) {
        SmartLearningResourceServer resourceServer = new SmartLearningResourceServer();
 
        try {
            // Start gRPC server
            Server server = ServerBuilder.forPort(port).addService(resourceServer).build().start();
 
            System.out.println("SmartLearningResource Server started on port " + port);
 
            // Register service with jmDNS using ServiceRegistration utility
            ServiceRegistration registration = ServiceRegistration.getInstance();
            registration.registerService(serviceType, serviceName, port, "Smart Learning Resource Management Service");
 
            System.out.println("SmartLearningResource Service registered with jmDNS");
            System.out.println("Service Type: " + serviceType);
            System.out.println("Service Name: " + serviceName);
 
            // Keep server running
            server.awaitTermination();
 
        } catch (IOException | InterruptedException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
 
    // UNARY: Get resource availability
    @Override
    public void getResourceAvailability(ResourceRequest request, StreamObserver<ResourceStatus> responseObserver) {
        
        Context ctx = Context.current();
        
        try {
            // Check for cancellation
            if (ctx.isCancelled()) {
                System.out.println("Request cancelled");
                responseObserver.onError(Status.CANCELLED.withDescription("Resource availability check cancelled").asRuntimeException());
                return;
            }
 
            String resourceId = request.getResourceId();
            System.out.println("Checking availability for resource: " + resourceId);
 
            // Validate input
            if (resourceId == null || resourceId.isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Resource ID cannot be empty").asRuntimeException());
                return;
            }
 
            // Search for resource in ArrayList
            ResourceData data = null;
            for (ResourceData resource : resourceDatabase) {
                if (resource.resourceId.equals(resourceId)) {
                    data = resource;
                    break;
                }
            }
            
            ResourceStatus.Builder statusBuilder = ResourceStatus.newBuilder();
            
            if (data != null) {
                statusBuilder.setAvailable(data.available).setLocation(data.location);
                
                System.out.println("Response sent: Available = " + data.available + ", Location = " + data.location);
            } else {
                // Resource not found - return NOT_FOUND status
                responseObserver.onError(Status.NOT_FOUND.withDescription("Resource ID '" + resourceId + "' not found in database").asRuntimeException());
                return;
            }
 
            ResourceStatus status = statusBuilder.build();
            responseObserver.onNext(status);
            responseObserver.onCompleted();
 
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error checking resource availability: " + e.getMessage()).asRuntimeException());
        }
    }
 
    // SERVER STREAMING: Stream available resources by category
    @Override
    public void streamAvailableResources(CategoryRequest request, StreamObserver<ResourceInfo> responseObserver) {
        
        Context ctx = Context.current();
        
        try {
            String category = request.getCategory();
            System.out.println("Streaming resources for category: " + category);
 
            // Validate input
            if (category == null) {
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Category cannot be null").asRuntimeException());
                return;
            }
 
            int count = 0;
            // Loop through ArrayList to find matching resources
            for (ResourceData data : resourceDatabase) {
                
                // Check for cancellation before each stream
                if (ctx.isCancelled()) {
                    System.out.println("Streaming cancelled by client");
                    responseObserver.onError(Status.CANCELLED.withDescription("Resource streaming cancelled").asRuntimeException());
                    return;
                }
                
                // Filter by category (simple simulation)
                if (data.available && (category.isEmpty() || data.format.toLowerCase().contains(category.toLowerCase()))) {
                    ResourceInfo info = ResourceInfo.newBuilder().setTitle(data.title).setAuthor(data.author).setFormat(data.format).build();
 
                    responseObserver.onNext(info);
                    count++;
                    System.out.println("Streamed resource: " + data.title);
 
                    // Simulate streaming delay with deadline awareness
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        responseObserver.onError(Status.ABORTED.withDescription("Streaming interrupted").asRuntimeException());
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
            responseObserver.onError(Status.INTERNAL.withDescription("Error streaming resources: " + e.getMessage()).asRuntimeException());
        }
    }
 
    // Helper class to store resource data
    private static class ResourceData {
        String resourceId;
        boolean available;
        String location;
        String title;
        String author;
        String format;
 
        ResourceData(String resourceId, boolean available, String location, String title, String author, String format) {
            this.resourceId = resourceId;
            this.available = available;
            this.location = location;
            this.title = title;
            this.author = author;
            this.format = format;
        }
    }
}