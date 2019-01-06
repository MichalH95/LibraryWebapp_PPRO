FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/projekt-0.0.1-SNAPSHOT.jar /app/projekt.jar
ENTRYPOINT ["java", "-jar", "/app/projekt.jar"]
