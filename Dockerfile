# Start with a base image that has the JDK already installed
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source code of your Spring Boot application to the container
COPY . /app

# Install Gradle in the container
RUN apk add --no-cache gradle

# Build the JAR file for your Spring Boot application inside the container
RUN gradle build

# Expose the port your application runs on
EXPOSE 8080

# Start the Spring Boot application when the container starts
CMD ["java", "-jar", "build/libs/stockMarket-0.0.1-SNAPSHOT.jar"]