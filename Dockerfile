FROM openjdk:17-slim-buster
COPY build/libs/escapezone-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]