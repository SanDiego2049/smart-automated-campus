/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartautomatedcampusservers.jmDNS;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
/**
 *
 * @author Penelope
 */


/**
 * ServiceDiscovery for gRPC clients using jmDNS.
 * 
 * gRPC clients use this utility to discover gRPC services on the local network.
 * Once discovered, clients can extract the host and port to create gRPC channels.
 */
public class ServiceDiscovery {
    private String requiredServiceType;
    private String requiredServiceName;
    private JmDNS jmdns;
    private ServiceInfo discoveredServiceInfo;

    /**
     * Constructor for ServiceDiscovery
     * 
     * @param serviceType the gRPC service type to discover (e.g., _smartclassroom._tcp.local.)
     * @param serviceName the gRPC service name to discover (e.g., SmartClassroomService)
     */
    public ServiceDiscovery(String serviceType, String serviceName) {
        this.requiredServiceType = serviceType;
        this.requiredServiceName = serviceName;
    }

    /**
     * Discovers a gRPC service on the local network
     * 
     * @param timeoutMilliseconds timeout for discovery in milliseconds
     * @return ServiceInfo of the discovered gRPC service, or null if not found
     * @throws InterruptedException if discovery is interrupted
     */
    public ServiceInfo discoverService(long timeoutMilliseconds) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        
        try {
            // Create a JmDNS instance
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            System.out.println("ServiceDiscovery: Searching for gRPC services at " + InetAddress.getLocalHost());
            
            // Add a service listener for the required gRPC service type
            jmdns.addServiceListener(requiredServiceType, new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    System.out.println("gRPC Service added: " + event.getInfo().getName());
                }

                @Override
                public void serviceRemoved(ServiceEvent event) {
                    System.out.println("gRPC Service removed: " + event.getInfo().getName());
                }

                @Override
                public void serviceResolved(ServiceEvent event) {
                    ServiceInfo info = event.getInfo();
                    String resolvedServiceName = info.getName();
                    int port = info.getPort();
                    
                    System.out.println("gRPC Service resolved: " + resolvedServiceName + " on port " + port);
                    
                    // Check if this is the gRPC service we are looking for
                    if (resolvedServiceName.contains(requiredServiceName)) {
                        System.out.println("Found required gRPC service: " + resolvedServiceName);
                        discoveredServiceInfo = info;
                        
                        // Service found - release the latch
                        latch.countDown();
                    }
                }
            });
        } catch (UnknownHostException e) {
            System.err.println("Unknown host exception: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException during gRPC service discovery: " + e.getMessage());
        }
        
        // Wait for gRPC service to be discovered or timeout
        boolean discovered = latch.await(timeoutMilliseconds, TimeUnit.MILLISECONDS);
        
        if (discoveredServiceInfo != null) {
            System.out.println("gRPC Service discovery successful!");
            System.out.println("Service: " + discoveredServiceInfo.getName());
            System.out.println("Host: " + getHost());
            System.out.println("Port: " + getPort());
        } else {
            System.out.println("gRPC Service discovery timed out - service not found");
        }
        
        return discoveredServiceInfo;
    }

    /**
     * Get the port of the discovered gRPC service
     * 
     * @return gRPC server port number, or -1 if service not discovered
     */
    public int getPort() {
        return discoveredServiceInfo != null ? discoveredServiceInfo.getPort() : -1;
    }

    /**
     * Get the host address of the discovered gRPC service
     * 
     * @return host address as string, or null if service not discovered
     */
    public String getHost() {
        if (discoveredServiceInfo != null && discoveredServiceInfo.getInetAddresses().length > 0) {
            return discoveredServiceInfo.getInetAddresses()[0].getHostAddress();
        }
        return null;
    }

    /**
     * Get the full address for creating a gRPC channel (host:port format)
     * 
     * @return address string in "host:port" format, or null if service not discovered
     */
    public String getGrpcAddress() {
        if (discoveredServiceInfo != null && getHost() != null) {
            return getHost() + ":" + getPort();
        }
        return null;
    }

    /**
     * Close the JmDNS instance
     * 
     * @throws IOException if close fails
     */
    public void close() throws IOException {
        if (jmdns != null) {
            jmdns.close();
            System.out.println("ServiceDiscovery: JmDNS instance closed");
        }
    }
}