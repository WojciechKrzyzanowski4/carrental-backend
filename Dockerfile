FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/
ENTRYPOINT ["java","-jar","/app/car-rental-app-0.0.1-SNAPSHOT.jar"]