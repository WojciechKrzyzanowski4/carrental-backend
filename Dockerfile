FROM gradle:8.5-jdk17 as build

WORKDIR /app

COPY . ./

RUN gradle clean build -x test

FROM openjdk:17-slim

EXPOSE 8080

COPY --from=build /app/build/libs/*.jar /app/

ENTRYPOINT ["java","-jar","/app/car-rental-app-0.0.1-SNAPSHOT.jar"]
