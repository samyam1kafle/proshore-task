# Battery Data Application Documentation

## Overview

This application is built upon Spring Boot and uses an in-memory database (H2). It includes two different endpoints: one for creating battery data and another for fetching the records based upon the postcode in alphabetical order along with the total of watt capacity and the average of watt capacity.
Technologies Used

    Spring Boot 3.0.4
    H2 in-memory database
    Java

## Prerequisites

    Java 19
    Maven

## Getting Started

To build and run the application, follow these steps:

1: Clone the repository
            
    git clone https://github.com/samyam1kafle/proshore-task.git

2: Navigate to the project root directory

    cd proshore-task

3: Build the application using Maven

    ./mvnw clean install 
    
    //Alternative
    ./mvnw clean
    ./mvnw install  

4: Run the application using the Spring Boot Maven plugin or using your IDE project runner
    
    ./mvnw spring-boot:run 
    or 
    you can simply use the IDE project runner.

5: The application will be running on http://localhost:8080/

# Endpoints and links

## API collection Link
[https://api.postman.com/collections/7648392-498bee69-f5bc-46a0-9439-72289e1bf174?access_key=PMAT-01GW6XMY7H55D8RGXK8N8MRA8S
](https://api.postman.com/collections/7648392-498bee69-f5bc-46a0-9439-72289e1bf174?access_key=PMAT-01GW6XMY7H55D8RGXK8N8MRA8S)
## API Documentation Link
[https://documenter.getpostman.com/view/7648392/2s93RMUuuh
](https://documenter.getpostman.com/view/7648392/2s93RMUuuh)

#### If any query regarding the above code feel free to email [samyam1kafle@gmail.com](samyam1kafle@gmail.com) also feel free to check all my skills and works by visiting my portfolio [samyamkafle.com.np](https://samyamkafle.com.np)
