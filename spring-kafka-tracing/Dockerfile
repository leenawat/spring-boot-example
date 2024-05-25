# Stage 1: Build the application
#FROM maven:3.8.4-openjdk-11-slim AS builder
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file
COPY spring-kafka-tracing/pom.xml .

# Download dependencies and cache them
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY spring-kafka-tracing/src ./src

# Build the application
RUN mvn package -DskipTests


# Stage 2: Package the application JAR for runtime
FROM openjdk:17-jre-slim AS runtime

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file from the previous stage
COPY --from=builder /app/target/*.jar ./app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Define the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]