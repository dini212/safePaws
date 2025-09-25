🐾 SafePaws – Animal Shelter & Adoption API

SafePaws is a Spring Boot REST API project designed to manage animal shelters, adoptions, and users with secure authentication using JWT.
This project was built to demonstrate backend development skills including API design, authentication, documentation, and testing.

🚀 Features

User Registration & Authentication (JWT-based)

Shelter Management (CRUD)

Animal Management (CRUD)

Adoption Flow (request & approval)

API Documentation (Swagger UI + Postman Collection)

Secure endpoints with JWT token

Database integration with MySQL

🛠️ Tech Stack

Language: Java (Spring Boot)

Database: MySQL

Authentication: JWT

Tools: Maven, Postman, SwaggerHub

Testing: JUnit (planned), Postman Collection

⚙️ Installation & Setup

Clone this repository:

git clone https://github.com/dini212/safePaws.git
cd safePaws


Configure your database in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/safepaws
spring.datasource.username=root
spring.datasource.password=yourpassword


Run the application:

mvn spring-boot:run


The API will be available at:

http://localhost:8080

📬 API Testing with Postman

You can test all SafePaws endpoints using the provided Postman collection:
📂 Download Collection

How to Use

Open Postman.

Import the collection file.

Set the environment variables (e.g., base URL, JWT token if needed).

Run the requests or execute the collection as a test suite.

📖 API Documentation with Swagger

SafePaws also provides interactive API documentation using Swagger UI.
Swagger allows you to explore endpoints, test requests, and view request/response details directly in your browser.

🔗 View API Documentation on SwaggerHub

Local Access

Start the SafePaws application.

Open your browser at:

http://localhost:8080/swagger-ui.html

or http://localhost:8080/swagger-ui/index.html

🗂️ Example Endpoints

Register User
POST /api/users/register

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "mypassword123"
}


Login User
POST /api/users/login

{
  "email": "john@example.com",
  "password": "mypassword123"
}


Get All Animals
GET /api/animals

📊 Project Structure
src/main/java/com/safepaws
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── config
 └── dto

