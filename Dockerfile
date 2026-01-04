# Stage 1: Build
FROM ubuntu:latest AS build

# Instalar dependencias
RUN apt-get update && apt-get install -y openjdk-17-jdk wget unzip curl git

# Establecer directorio de trabajo
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Dar permisos al wrapper de Gradle
RUN chmod +x ./gradlew

# Construir el jar de Spring Boot
RUN ./gradlew bootJar --no-daemon

# Stage 2: Runtime
FROM openjdk:17-jdk-slim

# Puerto donde correrá la app
EXPOSE 8080

# Copiar jar generado del stage build
COPY --from=build /app/build/libs/*.jar app.jar

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
