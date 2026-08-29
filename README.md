# 🐾 SafePaws – Animal Shelter & Adoption API

The animal adoption process is often hindered by manual record-keeping and decentralized communication between shelters and potential adopters. SafePaws serves as an integrated backend solution designed to digitalize and streamline these operational workflows.

This API facilitates animal data management, user registration, and an automated, secure adoption approval process. Built with a RESTful architecture, the system leverages Spring Boot RestClient to dynamically fetch shelter location and address data from a third-party external service.

# 🚀 Features
1.** Secure Authentication:** Implemented JSON Web Token (JWT) based security to protect API endpoints and validate user sessions effectively.
2.** Role-Based Adoption Workflow:** Structured the business logic to separate user roles; public users can submit adoption requests, while shelter administrators hold the full authority to review (accept or reject) these applications.
3.** Third-Party API Integration:** Successfully integrated an external API service using Spring Boot RestClient to enrich operational shelter data.
4. **Comprehensive Documentation:** All API endpoints are interactively documented using Swagger UI and thoroughly tested via a Postman Collection.

 # 🛠️ Tech Stack
1. Core: Java, Spring Boot, Spring Security
2. Database: MySQL
3. Tools & Build: Maven, Postman, SwaggerHub
4. 4.Testing: JUnit, Postman API Testing

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
