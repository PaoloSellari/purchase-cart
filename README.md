# README

This project implements the "Purchase Cart Service" coding test. It is written in Java 21 and uses Spring Boot v3.4.1
for the web stack and Spring Data JPA for the data persistence layer.

# Data Persistence

For simplicity, data is stored in an embedded H2 relational database. On the first execution, a `data.mv.db` file is created in
the mounted folder and populated with initial data. This allows data to persist across application
restarts.

**Note:** Unit tests run against an in-memory database and do not use this persistent data file. Therefore, running only
the tests will not create the data file if it doesn't already exist.

# Review Tasks

Follow these steps to build, test, and run the application using Docker.

## 1. Build the Execution Tool Image

This step creates the Docker image containing the necessary tools to build and test the application. It is required
before running tests or building the application artifact.

      cd sourcedir
      docker build -t mytest .

## 2. Execute Unit Tests

This command runs the unit tests inside the Docker container.

      cd sourcedir
      docker run -v $(pwd):/mnt -w /mnt mytest ./scripts/test.sh

## 3. Build Application Artifact

This command builds the executable JAR file for the application inside the Docker container. This step is required before running the application.

      cd sourcedir
      docker run -v $(pwd):/mnt -w /mnt mytest ./scripts/build.sh

## 4. Run Application

This command runs the application using the previously built JAR file. It requires the build artifact step (Step 3) to
have been completed successfully.

      cd sourcedir
      docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/run.sh

