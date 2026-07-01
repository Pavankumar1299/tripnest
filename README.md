# TripNest - Microservices Based Booking System

TripNest is a Spring Boot Microservices project that simulates an online travel booking platform. It allows users to register, log in, book hotels and flights, and cancel bookings. The project follows a distributed microservices architecture using Spring Cloud components.

---

## ✨ Features

### User Service
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption

### Hotel Service
- Add Hotel
- Update Hotel
- Delete Hotel
- View Hotels

### Flight Service
- Add Flight
- Update Flight
- Delete Flight
- View Flights

### Booking Service
- Hotel Booking
- Flight Booking
- Cancel Booking
- View Booking Details
- OpenFeign Communication

### Infrastructure
- Eureka Discovery Server
- Spring Cloud API Gateway
- JWT Authentication
- Role-Based Authorization (ADMIN / USER)
- Spring Security
- OpenFeign
- Swagger/OpenAPI Documentation
- Docker & Docker Compose
- Separate MySQL Database for Each Service

---

## 🏗️ Architecture

```
                  Client (Postman)
                         |
                  API Gateway (8080)
                         |
                 Eureka Discovery Server
                         |
 -----------------------------------------------------
 |                 |                |                |
User Service   Hotel Service   Flight Service   Booking Service
    |               |                |                |
 MySQL DB       MySQL DB        MySQL DB        MySQL DB
```

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Eureka Server
- OpenFeign
- Spring Data JPA
- Hibernate
- MySQL
- Docker
- Docker Compose
- Swagger (OpenAPI)
- JWT
- Maven
- Lombok
- Postman
- Git & GitHub

---

## 📂 Project Structure

```
TripNest
│
├── eureka-server
├── api-gateway
├── user-service
├── hotel-service
├── flight-service
├── booking-service
├── docker-compose.yml
├── init.sql
└── README.md
```

---

## ⚙️ Microservices

| Service | Port |
|----------|------|
| Eureka Server | 8761 |
| API Gateway | 8080 |
| User Service | 8083 |
| Hotel Service | 8081 |
| Flight Service | 8082 |
| Booking Service | 8084 |

---

## 🔄 API Flow

### Register

```
POST /users/register
```

### Login

```
POST /users/login
```

Returns JWT Token

### Hotel Booking

```
POST /bookings/hotel
```

### Flight Booking

```
POST /bookings/flight
```

### Cancel Booking

```
PUT /bookings/cancel/{id}
```

---

## 🔐 Authentication

TripNest uses JWT (JSON Web Token) authentication.

### Public APIs

- Register
- Login

### Protected APIs

- Hotel APIs
- Flight APIs
- Booking APIs

Example Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🔗 Service Communication

The Booking Service communicates with:

- User Service
- Hotel Service
- Flight Service

using Spring Cloud OpenFeign.

---

## 🗄️ Database

Each microservice maintains its own database.

| Service | Database |
|----------|-----------|
| User Service | user_db |
| Hotel Service | hotel_db |
| Flight Service | flight_db |
| Booking Service | booking_db |

---

## ▶️ How to Run

### Option 1: Run Locally

#### Clone Repository

```bash
git clone https://github.com/<your-username>/TripNest.git
```

#### Create Databases

```
user_db
hotel_db
flight_db
booking_db
```

#### Start Services

Run the services in the following order:

1. Eureka Server
2. API Gateway
3. User Service
4. Hotel Service
5. Flight Service
6. Booking Service

Open Eureka Dashboard

```
http://localhost:8761
```

---

### Option 2: Run with Docker

Build the project

```bash
mvn clean package
```

Build Docker images

```bash
docker compose build
```

Start all services

```bash
docker compose up
```

or

```bash
docker compose up -d
```

Open Eureka Dashboard

```
http://localhost:8761
```

Stop containers

```bash
docker compose down
```

---

## 📚 API Documentation

Swagger UI

```
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
http://localhost:8083/swagger-ui/index.html
http://localhost:8084/swagger-ui/index.html
```

## 🔐 Authentication

TripNest uses JWT Authentication with Role-Based Authorization.

### Public APIs

- Register
- Login

### ADMIN

- Manage Hotels
- Manage Flights

### USER

- View Hotels
- View Flights
- Book Hotels
- Book Flights
- View Own Bookings

Example Header

```
Authorization: Bearer <JWT_TOKEN>
```

## 📈 Future Enhancements

- Payment Service
- Email Notification
- Booking History
- Hotel & Flight Search Filters
- Circuit Breaker (Resilience4j)
- Kafka Event-Driven Communication

---

## 👨‍💻 Author

**Pavankumar Pattar**

Java Backend Developer

Tech Stack:
Java • Spring Boot • Spring Security • Spring Cloud • Hibernate • JPA • MySQL • OpenFeign • JWT • Maven • Git • SwaggerUI • Docker

---

⭐ If you like this project, consider giving it a Star.