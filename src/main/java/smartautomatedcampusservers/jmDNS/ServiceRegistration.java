/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartautomatedcampusservers.jmDNS;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
/**
 *
 * @author Penelope
 */

/**
 * ServiceRegistration for gRPC services using jmDNS.
 * Uses the Singleton pattern - only one instance can exist.
 * 
 * gRPC servers call this utility to register themselves with jmDNS
 * so that gRPC clients can discover them on the local network.
 */
public class ServiceRegistration {
    private static JmDNS jmdns;
    private static ServiceRegistration theRegister;

    /**
     * Private constructor creates the JmDNS register object
     */
    private ServiceRegistration() throws UnknownHostException, IOException {
        jmdns = JmDNS.create(InetAddress.getLocalHost());
        System.out.println("ServiceRegistration: Created JmDNS instance at " + InetAddress.getLocalHost());
    }

    /**
     * gRPC servers call getInstance() to get the singleton instance of the register
     *
     * @return ServiceRegistration singleton instance
     * @throws IOException if JmDNS creation fails
     */
    public static ServiceRegistration getInstance() throws IOException {
        if (theRegister == null) {
            theRegister = new ServiceRegistration();
        }
        return theRegister;
    }

    /**
     * gRPC servers call registerService to register themselves for discovery
     *
     * @param type fully qualified service type name (e.g., _smartclassroom._tcp.local.)
     * @param name service instance name (e.g., SmartClassroomService)
     * @param port the gRPC server port
     * @param description text describing the gRPC service
     * @throws IOException if service registration fails
     */
    public void registerService(String type, String name, int port, String description) throws IOException {
        // Create service info for gRPC service
        ServiceInfo serviceInfo = ServiceInfo.create(type, name, port, description);
        
        // Register the gRPC service with jmDNS
        jmdns.registerService(serviceInfo);
        System.out.println("Registered gRPC Service: " + name + " on port " + port);
        System.out.println("Service Type: " + type);
        System.out.println("Description: " + description);
    }
}