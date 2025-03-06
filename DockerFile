# --- Etapa 1: Construir el JAR con Maven ---
FROM maven:3.8.6-amazoncorretto-21 AS builder

WORKDIR /app
COPY pom.xml .
# Descarga dependencias primero (para cachear)
RUN mvn dependency:go-offline

COPY src ./src
# Compila el proyecto y genera el JAR
RUN mvn clean package -DskipTests

# --- Etapa 2: Crear la imagen final ---
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
# Copia el JAR desde la etapa de construcción
COPY --from=builder /app/target/Desarrollo-Corpelima-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]