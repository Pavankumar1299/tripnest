# TripNest - Microservices Based Booking System

TripNest is a Spring Boot Microservices project that simulates an online travel booking platform. It allows users to register, log in, book hotels and flights, and cancel bookings. The project follows a distributed microservices architecture using Spring Cloud components.

---

## 🚀 Features

### User Service
- User Registration
- User Login
- JWT Token Generation
- Password Encryption using BCrypt

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
- OpenFeign Communication with Hotel, Flight and User Services

### Infrastructure
- Eureka Discovery Server
- Spring Cloud API Gateway
- Spring Security
- JWT Authentication
- OpenFeign
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
- Maven
- Lombok
- JWT
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

### 1. Clone Repository

```bash
git clone https://github.com/<your-username>/TripNest.git
```

### 2. Create Databases

```
user_db
hotel_db
flight_db
booking_db
```

### 3. Start Services

Run services in the following order:

1. Eureka Server
2. API Gateway
3. User Service
4. Hotel Service
5. Flight Service
6. Booking Service

---

## 📬 Sample APIs

### Register User

```
POST /users/register
```

### Login

```
POST /users/login
```

### Add Hotel

```
POST /hotels
```

### Add Flight

```
POST /flights
```

### Book Hotel

```
POST /bookings/hotel
```

### Book Flight

```
POST /bookings/flight
```

---

## 📈 Future Enhancements

- Docker & Docker Compose
- Swagger/OpenAPI Documentation
- Role-Based Authorization (ADMIN / USER)
- Payment Service
- Email Notification
- Booking History
- Hotel & Flight Search Filters
- Circuit Breaker (Resilience4j)
- Kafka Event-Driven Communication

---

## 👨‍💻 Author

**Pavan Pattar**

Java Backend Developer

Tech Stack:
Java • Spring Boot • Spring Security • Spring Cloud • Hibernate • JPA • MySQL • OpenFeign • JWT • Maven • Git

---

⭐ If you like this project, consider giving it a Star.