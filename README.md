HotelBay-Cucumber-Project

RESTful API for hotel management built with Spring Boot, following a BDD approach with Cucumber.

How to run with Docker

To start the database and the application:
docker-compose up --build

The application will be available at:
http://localhost:8080

To stop the containers:
docker-compose down

How to run the tests
mvn test

docker-compose.yml structure

The docker-compose.yml file defines two services:


hotelbay-db: PostgreSQL database, exposed on port 5432
hotelbay-app: Spring Boot application, exposed on port 8080


Both services communicate through an internal Docker network.