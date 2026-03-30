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


// ServiceRegistration for gRPC services using jmDNS.


public class ServiceRegistration {
    private static JmDNS jmdns;
    private static ServiceRegistration theRegister;

    
   // Private constructor creates the JmDNS register object
     
    private ServiceRegistration() throws UnknownHostException, IOException {
        jmdns = JmDNS.create(InetAddress.getLocalHost());
        System.out.println("ServiceRegistration: Created JmDNS instance at " + InetAddress.getLocalHost());
    }

    
    // gRPC servers call getInstance() to get the singleton instance of the register
    public static ServiceRegistration getInstance() throws IOException {
        if (theRegister == null) {
            theRegister = new ServiceRegistration();
        }
        return theRegister;
    }

    
    // gRPC servers call registerService to register themselves for discovery
     
    public void registerService(String type, String name, int port, String description) throws IOException {
        // create service info for gRPC service
        ServiceInfo serviceInfo = ServiceInfo.create(type, name, port, description);
        
        // register the gRPC service with jmDNS
        jmdns.registerService(serviceInfo);
        System.out.println("Registered gRPC Service: " + name + " on port " + port);
        System.out.println("Service Type: " + type);
        System.out.println("Description: " + description);
    }
}