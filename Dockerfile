FROM ubuntu:latest
LABEL authors="aerop"

ENTRYPOINT ["top", "-b"]

# Etapa de construcción
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=build /app/target/plot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
