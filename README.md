🚗 Smart Parking Management System

🎯 Overview
The Smart Parking Management System addresses urban parking challenges through intelligent space allocation and management. The platform enables drivers to locate and reserve parking spaces while providing owners with dynamic management tools and secure payment processing.

Infrastructure Services

🌐 Eureka Server - Service registry and discovery
⚙️ Config Server - Centralized configuration management
🚪 API Gateway - Request routing and load balancing

Business Services

🅿️ Parking Space Service - Space availability and reservations
🚙 Vehicle Service - Vehicle registration and tracking
👤 User Service - Authentication and profile management
💳 Payment Service - Transaction processing and receipts

🛠️ Tech Stack
Primary framework uses Spring Boot with Spring Cloud components including Eureka for service discovery, Config Server for configuration management, and Gateway for API routing. Optional Node.js Express or Python Flask may be used for specific services.

📁 Project Structure
🏗️ AAD2-Final-Project/
├── 🌐 eureka-server/
├── ⚙️ config-server/  
├── 🚪 api-gateway/
├── 🅿️ parking-space-service/
├── 🚙 vehicle-service/
├── 👤 user-service/
├── 💳 payment-service/
├── 📚 docs/screenshots/
└── 📋 postman_collection.json

🚀 Setup
Prerequisites: Java 11+, Maven 3.6+
Start Order:

Eureka Server (port 8761)
Config Server (port 8888)
Business services (ports 8081-8084)
API Gateway (port 8080)

Verification: Access Eureka Dashboard at http://localhost:8761

✨ Features
The system provides real-time parking space search and reservation, dynamic status updates through manual or IoT integration, secure payment processing with digital receipts, vehicle entry/exit tracking simulation, and comprehensive booking history with analytics.

🧪 Testing
Complete API validation through Postman collections testing all microservice endpoints, error handling, and service integration via the API Gateway.
📋 Resources

📋 Postman Collection
Show Image

