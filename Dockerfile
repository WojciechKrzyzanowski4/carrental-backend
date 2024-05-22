# Stage 1: Build
FROM gradle:8.5-jdk17 as build

WORKDIR /app

COPY . ./

# Stage 2: Run
RUN gradle clean build -x test

FROM openjdk:17-slim

# the exposed port inside the container
EXPOSE 8080

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/*.jar /app/

# Copy the application-local.properties file into the container
COPY src/main/resources/application-docker.properties /conf//ig/application.properties

# Set the entrypoint and specify the location of the properties file
ENTRYPOINT ["java", "-Dspring.config.location=file:/config/application.properties", "-jar", "/app/car-rental-app-0.0.1-SNAPSHOT.jar"]
