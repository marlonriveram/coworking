# -------- ESTAPA 1: Construcción (Build) --------
# Usamos una imagen de Maven que ya viene con Java 21 para compilar
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Directorio donde trabajaremos dentro de Docker
WORKDIR /app

# Copiamos solo el pom.xml primero para descargar las dependencias
# Esto acelera futuras construcciones (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Ahora copiamos el código fuente y compilamos el archivo JAR
COPY src ./src
RUN mvn clean package -DskipTests

# -------- ETAPA 2: Ejecución (Run) --------
# Usamos una imagen de Java mucho más ligera solo para correr la app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
# Nota: Usamos el nombre que vimos en tu carpeta target
COPY --from=build /app/target/sistema_de_reserva_coworking-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]