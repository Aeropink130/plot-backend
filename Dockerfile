# Etapa de construcción
FROM maven:3.8.8-openjdk-17-slim AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y construir la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM amazoncorretto:17
VOLUME /tmp
COPY --from=build /app/target/plot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
