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



// ServiceDiscovery for gRPC clients using jmDNS.

public class ServiceDiscovery {
    private String requiredServiceType;
    private String requiredServiceName;
    private JmDNS jmdns;
    private ServiceInfo discoveredServiceInfo;

    
    // Constructor
    public ServiceDiscovery(String serviceType, String serviceName) {
        this.requiredServiceType = serviceType;
        this.requiredServiceName = serviceName;
    }

    
    //discovers a gRPC service on the local network
    public ServiceInfo discoverService(long timeoutMilliseconds) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        
        try {
            // create a JmDNS instance
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            System.out.println("ServiceDiscovery: Searching for gRPC services at " + InetAddress.getLocalHost());
            
            //add a service listener for the required gRPC service type
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
                    
                    //check if this is the gRPC service we are looking for
                    if (resolvedServiceName.contains(requiredServiceName)) {
                        System.out.println("Found required gRPC service: " + resolvedServiceName);
                        discoveredServiceInfo = info;
                        
                        // service found
                        latch.countDown();
                    }
                }
            });
        } catch (UnknownHostException e) {
            System.err.println("Unknown host exception: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException during gRPC service discovery: " + e.getMessage());
        }
        
        //wait for gRPC service to be discovered
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

    
    // get the port of the discovered gRPC service
    public int getPort() {
        return discoveredServiceInfo != null ? discoveredServiceInfo.getPort() : -1;
    }

   
   // get the host address of the discovered gRPC service
    public String getHost() {
        if (discoveredServiceInfo != null && discoveredServiceInfo.getInetAddresses().length > 0) {
            return discoveredServiceInfo.getInetAddresses()[0].getHostAddress();
        }
        return null;
    }

    
    //gets the full address for creating a gRPC channel (host:port format)
    public String getGrpcAddress() {
        if (discoveredServiceInfo != null && getHost() != null) {
            return getHost() + ":" + getPort();
        }
        return null;
    }

    // Close the JmDNS instance

    public void close() throws IOException {
        if (jmdns != null) {
            jmdns.close();
            System.out.println("ServiceDiscovery: JmDNS instance closed");
        }
    }
}