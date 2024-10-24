FROM ubuntu:latest
LABEL authors="aerop"

ENTRYPOINT ["top", "-b"]

# Etapa de construcción
FROM maven:3.8.8-openjdk-17 AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y construir la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=build /app/target/plot-backend.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
