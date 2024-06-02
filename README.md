# Payment Service

## Overview

Payment Service is a Spring Boot application designed to facilitate online payments. This service integrates with Razorpay for processing payments and provides APIs to handle payment statuses through Razorpay webhooks. The application is built using Spring Boot, Spring Data JPA, Spring Web, Maven, and MySQL.

## Table of Contents

1. [Requirements](#requirements)
2. [Installation](#installation)
3. [Configuration](#configuration)
4. [Usage](#usage)
5. [API Endpoints](#api-endpoints)
6. [Webhook Handling](#webhook-handling)
7. [Database Schema](#database-schema)
8. [Project Structure](#project-structure)
9. [Running Tests](#running-tests)
10. [Contributing](#contributing)
11. [License](#license)

## Requirements

- Java 11 or higher
- Maven 3.6.3 or higher
- MySQL 8.0 or higher
- Razorpay account for API keys

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/payment-service.git
   cd payment-service
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Set up MySQL database:**
   ```sql
   CREATE DATABASE payment_service;
   ```

4. **Update application properties:**

   Edit the `src/main/resources/application.properties` file to configure your database and Razorpay credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/payment_service
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update

   razorpay.key_id=your_razorpay_key_id
   razorpay.key_secret=your_razorpay_key_secret
   ```

## Configuration

The configuration for the application is primarily managed through the `application.properties` file. Key properties include:

- **Database Configuration:**
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/payment_service
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  spring.jpa.hibernate.ddl-auto=update
  ```

- **Razorpay API Credentials:**
  ```properties
  razorpay.key_id=your_razorpay_key_id
  razorpay.key_secret=your_razorpay_key_secret
  ```

## Usage

1. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

2. The application will start on `http://localhost:8080`.

## Webhook Handling

Razorpay sends payment status updates to the configured webhook URL. Our application handles these webhooks to update the payment status in our database.

- **Webhook Endpoint:** `/api/payments/webhook`
- **Method:** `POST`
- **Description:** Handles Razorpay webhooks for payment status updates.
- **Request Body:** Razorpay webhook payload.
- **Response:** `200 OK`

## Database Schema

The database schema consists of a `payments` table:

```sql
CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id VARCHAR(255) NOT NULL,
    amount INT NOT NULL,
    currency VARCHAR(3) NOT NULL,
    receipt VARCHAR(255),
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## Project Structure

```
payment-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── payment/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── PaymentServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── payment/
│                       └── PaymentServiceApplicationTests.java
├── .gitignore
├── README.md
├── pom.xml
└── mvnw
```

## Running Tests

1. **Unit Tests:**
   ```bash
   mvn test
   ```

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
