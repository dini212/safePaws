# Online Animal Shelter

## Description 
This project is the final project of the Java Development Bootcamp, conducted in a group. This project aims to facilitate the adoption of stray animals in need of new homes by the general public through an online platform. Within this application, users can submit adoption requests to shelters, which have the ability to review and respond to these requests by either accepting or rejecting them. In this project, we use Java Spring Boot RestClient API for retrieving shelter addresses and JWT for authentication.

## Key Features
1. Register as a shelter or a regular user on our app
2. List available animals for adoption by regular users
3. Regular users can propose adopting an animal from the available list
4. Shelters can accept or reject adoption proposals from regular users

## Features Available for User
1. Registration User / Potential Adopter
2. Login User / Potential Adopter
3. Profile Management
4. Adoption

## Features Available for Admin Shelter
1. Registration User  Shelter
2. Login User Shelter
3. Management Profile Shelter
4. Management  Animal
5. Adoption decision

## How Safe Paws Work
1. Register
   - Register
   - add address data
2. Login
   - Authentication Username & Password
   - Return JWT Token
3. Adoption Request
   - Potential adopters can browse through animal profiles that only has status AVAILABLE.
   - Once an adopter finds a suitable animal, they can submit an adoption request through the API.
   - he schedule for home inspection will automatically shown the day after adoption request.
   - the day after adoption request already been scheduled by another potential adopters then it will choose the day after that.

Outcome:
- If the home environment is deemed worthy, the adoption process proceeds, the animal finds a new home, And the animal status will turn into NOT-AVAILABLE.
- If the environment is not suitable, the shelter staff has the right to reject the adoption request.

## API Testing with Postman
You can test all SafePaws endpoints using the provided Postman collection:
[Download Collection](./Safe%20Paws%20-%20FINAL.postman_collection.json) Or Click folder postman collection from above.

### How to Use
1. Open Postman.
2. Import the collection file.
3. Set the environment variables (e.g., base URL, JWT token if needed).
4. Run the requests or execute the collection as a test suite.

## API Documentation with Swagger

SafePaws provides interactive API documentation using **Swagger UI**.  
Swagger allows you to explore endpoints, test requests, and see request/response details directly in the browser.

🔗 [View API Documentation on SwaggerHub](https://app.swaggerhub.com/apis-docs/AndhikaPranadipa/safe-paws/1.0.0)

### How to Access Locally
1. Run the SafePaws Spring Boot application.
2. Open your browser at:
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
   - or [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) (depending on Spring Boot version).


