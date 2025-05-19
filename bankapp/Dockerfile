# Dockerfile for building and running a Spring Boot application with Gradle
#
# Steps:
# 1. Use OpenJDK 17 on Alpine Linux as the base image for a lightweight Java environment.
# 2. Update package lists and install Gradle for building the project.
# 3. Set the working directory to /app inside the container.
# 4. Copy all project files from the host to the container's /app directory.
# 5. Build the project using Gradle, producing the executable JAR.
# 6. Expose port 8080 so the app can receive HTTP requests.
# 7. Set the default command to run the built JAR file using Java.

# Multi-stage build for Spring Boot application
FROM gradle:8.7.0-jdk17-alpine AS build

# Copy project files
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Build project
RUN gradle build --no-daemon

# Runtime stage
FROM openjdk:17-alpine

# Create appuser for security
RUN adduser -D appuser
USER appuser

RUN apk update && apk add ca-certificates wget
RUN wget -P /tmp/certs/ https://s3.amazonaws.com/rds-downloads/rds-combined-ca-bundle.pem

# Copy built jar from build stage
COPY --from=build --chown=appuser:appuser /home/gradle/src/build/libs/*.jar /app/app.jar

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]