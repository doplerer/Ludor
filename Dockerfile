# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-22 AS build

# Establecer el directorio de trabajo
WORKDIR /

# Copiar el código fuente del proyecto al contenedor
COPY . .

# Construir el proyecto
RUN mvn clean package -DskipTests

# Etapa final para ejecutar la aplicación
FROM openjdk:22

# Copiar el archivo .jar generado desde la etapa de construcción
COPY --from=build /target/*.jar /ludor.jar

# Establecer el directorio de trabajo
WORKDIR /

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "ludor.jar"]
