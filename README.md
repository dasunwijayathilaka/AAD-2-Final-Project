# 🚗 **Smart Parking Management System**

# 🎯 **Overview**

The **Smart Parking Management System (SPMS)** addresses urban parking challenges through intelligent space allocation and dynamic management. The platform enables drivers to locate and reserve parking spaces in real time, while providing parking space owners with tools for live monitoring, configuration, and secure payment processing.

---

# 🛠️ **Infrastructure Services**

- 🌐 **Eureka Server** – Service registry and discovery  
- ⚙️ **Config Server** – Centralized configuration management  
- 🚪 **API Gateway** – Request routing and load balancing

---

# 💼 **Business Services**

- 🅿️ **Parking Space Service** – Space availability and reservation logic  
- 🚙 **Vehicle Service** – Vehicle registration, tracking, and entry/exit simulation  
- 👤 **User Service** – Authentication, profile management, and history  
- 💳 **Payment Service** – Mock payment validation and receipt generation

---

# 🧰 **Tech Stack**

> The system is built using **Spring Boot** and **Spring Cloud**.  
Optional microservices may also use:
- Node.js (Express)
- Python (Flask)

**Spring Components Used:**
- Spring Boot
- Spring Cloud Config
- Spring Cloud Eureka
- Spring Cloud Gateway
- Spring Web

---

# 📁 **Project Structure**

SPMS-Project/
├── config-server/              # Spring Cloud Config Server
├── eureka-server/              # Eureka Service Registry
├── api-gateway/                # Spring Cloud Gateway
├── parking-service/            # Microservice: Parking management
├── vehicle-service/            # Microservice: Vehicle tracking
├── user-service/               # Microservice: User and owner data
├── payment-service/            # Microservice: Payment simulation
├── docs/
│   └── screenshots/
│       └── eureka_dashboard.png
├── postman_collection.json     # Exported Postman Collection
├── README.md                   # Required project references and description
└── pom.xml / build.gradle      # Root project build config (if using multi-module)

---

# 🚀 **Setup & Run**

**🔧 Prerequisites:**
- Java 11+
- Maven 3.6+

**Start Order:**
1. 🌐 Eureka Server – `http://localhost:8761`
2. ⚙️ Config Server – `http://localhost:8888`
3. 🅿️ 🚙 👤 💳 Business Services – `ports 8081 - 8084`
4. 🚪 API Gateway – `http://localhost:8080`

**✅ Verification:**
- Open Eureka Dashboard: [http://localhost:8761](http://localhost:8761)
- All services should be visible and registered

---

# ✨ **Features**

- 🔎 Real-time search for parking spaces  
- 📡 Dynamic updates via manual or IoT status  
- 💳 Secure payment simulation with digital receipts  
- 🚗 Vehicle tracking and simulated entry/exit  
- 📜 Booking history and user logs  
- 📊 Zone-wise and owner-wise usage tracking

---

# 🧪 **API Testing**

- ✅ All endpoints validated with **Postman**  
- ❗ Thorough error handling and edge case testing  
- 🔁 Verified service-to-service communication via API Gateway

---

# 📋 **Resources**

- [📋 Postman Collection](./postman_collection.json)  
- ![Eureka Dashboard](./docs/screenshots/eureka_dashboard.png)

---

# 👨‍💻 **Author**

**Dasun Wijayathilaka**  
📍 Colombo, Sri Lanka  
