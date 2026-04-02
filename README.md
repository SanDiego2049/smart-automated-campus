# Smart Automated Campus

A distributed systems project implementing smart campus automation using gRPC and Java, supporting UN Sustainable Development Goal 4: Quality Education.

## Overview

The Smart Automated Campus system consists of three independent gRPC services that automate campus operations:

- **Smart Classroom Service** - Digital attendance tracking and real-time classroom interaction
- **Smart Learning Resource Service** - Library resource search and availability checking  
- **Smart Assessment Service** - Digital examinations, grading, and integrity monitoring

Services use jmDNS for automatic discovery - no hardcoded IP addresses needed.

## Features

- **All Four gRPC Communication Patterns** - Unary, Server Streaming, Client Streaming, Bidirectional Streaming
- **Automatic Service Discovery** - Using jmDNS (Multicast DNS)
- **Error Handling** - Comprehensive gRPC status codes
- **Cancellation Handling** - Client disconnect handling
- **Metadata Support** - Custom headers for client identification
- **Swing GUI Client** - Tabbed interface for all three services

## Technology Stack

- Java
- gRPC 1.15.1
- Protocol Buffers 3.6.1
- jmDNS 3.5.9
- Java Swing

## Installation

Clone and build:
```bash
git clone https://github.com/SanDiego2049/smart-automated-campus.git
cd smart-automated-campus
```

## Running the Application

Start each server in separate terminals:
```bash
# Terminal 1 - Classroom Service (Port 50051)
java -cp target/classes servers.SmartClassroomServer

# Terminal 2 - Resource Service (Port 50052)
java -cp target/classes servers.SmartLearningResourceServer

# Terminal 3 - Assessment Service (Port 50053)
java -cp target/classes servers.SmartAssessmentServer
```

Start the client:
```bash
# Terminal 4 - Client GUI
java -cp target/classes client.SmartCampusClientGUI
```

## Service Details

### Smart Classroom Service (Port 50051)
- `UploadAttendanceRecords` - Client Streaming
- `LiveClassInteraction` - Bidirectional Streaming

### Smart Learning Resource Service (Port 50052)
- `GetResourceAvailability` - Unary RPC
- `StreamAvailableResources` - Server Streaming

### Smart Assessment Service (Port 50053)
- `GetAssessmentDetails` - Unary RPC
- `SubmitAssessmentAnswers` - Client Streaming
- `StreamAssessmentResults` - Server Streaming
- `LiveAssessmentMonitoring` - Bidirectional Streaming

## Project Structure
```
smart-automated-campus/
├── src/main/
│   ├── proto/                                   # Protocol Buffer definitions
│   └── java/
│       ├── generated/                           # gRPC generated files
│       └── smartautomatedcampusservers/     
│           ├── client/                          # GUI client 
│           ├── jmDNS/                           # ServiceRegistration & ServiceDiscovery
│           └── servers/                         # Three gRPC server implementations
│               ├── SmartAssessmentServer.java                       
│               ├── SmartClassroomServer.java                  
│               └── SmartLearningResourceServer.java
└── pom.xml
```

