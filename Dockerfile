# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiamos el jar
COPY target/*.jar app.jar

# Puerto que usa Spring Boot
EXPOSE 8080

# Comando para arrancar la app
ENTRYPOINT ["java","-jar","app.jar"]