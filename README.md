# 🐾 SafePaws – Animal Shelter & Adoption API

This project is the final project of the Java Development Bootcamp, conducted in a group. This project aims to facilitate the adoption of stray animals in need of new homes by the general public through an online platform. Within this application, users can submit adoption requests to shelters, which have the ability to review and respond to these requests by either accepting or rejecting them. In this project, we use Java Spring Boot RestClient API for retrieving shelter addresses and JWT for authentication.

# 🚀 Features
1. User Registration & Authentication (JWT-based)
2. Shelter Management (CRUD)
3. Animal Management (CRUD)
4. Adoption Flow (request & approval)
5. API Documentation (Swagger UI + Postman Collection)
6. Secure endpoints with JWT token
7. Database integration with MySQL

 # 🛠️ Tech Stack
- Language: Java (Spring Boot)
- Database: MySQL
- Authentication: JWT
- Tools: Maven, Postman, SwaggerHub
- Testing: JUnit (planned), Postman Collection

# ⚙️ Installation & Setup
### Clone this repository:

  git clone https://github.com/dini212/safePaws.git
  cd safePaws

### Configure your database in application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/safepaws
spring.datasource.username=root
spring.datasource.password=yourpassword

### Run the application:
mvn spring-boot:run


### The API will be available at:
👉 http://localhost:8080

# 📬 API Testing with Postman

You can test all SafePaws endpoints using the provided Postman collection:  
📂 [Download Collection](./Safe%20Paws%20-%20FINAL.postman_collection.json)

### How to Use
1. Open Postman.
2. Import the collection file.
3. Set the environment variables (e.g., base URL, JWT token if needed).
4. Run the requests or execute the collection as a test suite.

# 📖 API Documentation with Swagger

SafePaws also provides interactive API documentation using Swagger UI.
Swagger allows you to explore endpoints, test requests, and view request/response details directly in your browser.

🔗 [View API Documentation on SwaggerHub](https://app.swaggerhub.com/apis-docs/AndhikaPranadipa/safe-paws/1.0.0)

### Local Access

1. Start the SafePaws application.
2. Open your browser at:
   - http://localhost:8080/swagger-ui.html
   - or http://localhost:8080/swagger-ui/index.html

# 🗂️ Example Endpoints

### Register User

#### POST /api/users/register

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "mypassword123"
}

### Login User

#### POST /api/users/login

{
  "email": "john@example.com",
  "password": "mypassword123"
}

### Get All Animals

#### GET /api/animals
