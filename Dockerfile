# --- Etapa 1: Construir el JAR con Maven y Java 21 (Eclipse Temurin) ---
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app
COPY pom.xml .
# Descarga de dependencias (para cachear)
RUN mvn dependency:go-offline

COPY src ./src
# Compila el proyecto y genera el JAR (sin tests)
RUN mvn clean package -DskipTests

# --- Etapa 2: Crear la imagen final con Java 21 (Eclipse Temurin) ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia el JAR desde la etapa de construcción
COPY --from=builder /app/target/Plataforma-Educativa-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
