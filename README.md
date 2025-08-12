# Ecommerce Backend

Welcome to the **Backend Of AETHER** repository! This is a monolithic Spring Boot application that powers a full-featured e-commerce platform, handling everything from user authentication to payment processing and address management.



## Overview
This repository contains the backend for an e-commerce application named **Aether**. It provides a robust set of APIs for managing user accounts, products, orders, payments, addresses, and more. The application uses Spring Boot with MySQL as the database, JWT and OAuth2 (Google) for authentication, and SMTP for email notifications.

## Features
- **User Management**: Register, login, and manage user profiles with JWT and OAuth2 (Google Sign-In).
- **Product Management**: CRUD operations for products, including categories and inventory.
- **Order Processing**: Create, view, and manage customer orders.
- **Payment Integration**: Secure payment processing for transactions.
- **Address Management**: Add, update, and delete user addresses.
- **Email Notifications**: Send confirmation emails using SMTP (Gmail).
- **RESTful APIs**: Comprehensive API endpoints for all e-commerce functionalities.

## Technologies Used
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL
- **Authentication**: JWT, OAuth2 (Google Sign-In)
- **Email Service**: SMTP (Gmail)
- **ORM**: Hibernate (Spring Data JPA)
- **Other Dependencies**: Spring Security, Spring Mail, MySQL Connector

## Installation
Follow these steps to set up the project locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/pathak77/Backend.git
   cd Backend

2. **Set up MySQL**:
    Ensure MySQL is installed and running on localhost:3306.
    Create a database (e.g., ecommerce_db).
    Update the database name, username, and password in src/main/resources/application.properties.

3. **Set up Dependencies**:
    Ensure you have Maven installed.
    Run the following command to download dependencies:
    ```bash
    mvn clean install

4. **Configure environment variables**:
    Create or update the src/main/resources/application.properties file with the following settings:
    ```bash
        spring.application.name=Ecommerce
        server.port=8080
        server.servlet.context-path=/api
        spring.jpa.database=mysql
        spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
        spring.datasource.username=<your-username>
        spring.datasource.password=<your-password>
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.hibernate.ddl-auto=update

        # JWT Configuration
        jwt.application.name=aether
        security.jwt.secret-key=<your-randomly-generated-secret-key>
        security.jwt.expiration-time=3600000

        # SMTP Configuration (Gmail)
        spring.mail.host=smtp.gmail.com
        spring.mail.port=587
        spring.mail.username=<your-smtp-mail-address>
        spring.mail.password=<your-smtp-mail-password>
        spring.mail.properties.mail.smtp.auth=true
        spring.mail.properties.mail.smtp.starttls.enable=true
        spring.mail.properties.mail.smtp.starttls.required=true
        spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com

        # OAuth2 Configuration (Google)
        spring.security.oauth2.client.registration.google.client-id=<your-google-client-id>
        spring.security.oauth2.client.registration.google.client-secret=<your-google-client-secret>
        spring.security.oauth2.client.registration.google.scope=profile,email
        spring.security.oauth2.client.registration.google.redirect-uri=<your-redirect-uri>
        spring.security.oauth2.client.registration.google.authorization-grant-type=authorization_code
        spring.security.oauth2.client.registration.google.client-name=Google
        spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
        spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
        spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo

5. **Run the application**:

## Configuration
    Database: Replace <your-database>, <username>, and <password> in application.properties with your MySQL database details.
    
    JWT: Generate a secure secret key for security.jwt.secret-key (e.g., using a random string generator).

    SMTP: Use a valid Gmail account for spring.mail.username and an App Password for spring.mail.password.

    OAuth2: Obtain Google client ID and secret from the Google Cloud Console and set the redirect URI appropriately.

## API Endpoints

    Below are some example API endpoints (update based on your implementation):

**User Management**:

POST /api/auth/register - Register a new user
POST /api/auth/login - Login and receive JWT
GET /api/users/profile - Get user profile (JWT required)

**Product Management**:
GET /api/products - List all products
POST /api/products - Add a new product (JWT required)

**Order Management**:
POST /api/orders - Create a new order (JWT required)
GET /api/orders - List user orders (JWT required)



**Payment**:

POST /api/payments - Process a payment (JWT required)

**Address Management**:

POST /api/address - Add a new address (JWT required)

DELETE /api/address/address_id - List user addresses (JWT required)

Note: Use tools like Postman or cURL to test APIs. Include the JWT token in the Authorization header as Bearer <token> for protected endpoints.

## Authentication


JWT: After login, a JWT token is issued, valid for 1 hour (3600000 ms). Use this token for authenticated requests.

OAuth2 (Google): Users can sign in via Google. The redirect URI must match the one configured in the Google Cloud Console.