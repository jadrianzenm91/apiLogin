# Etapa 1: Compilar con Maven y Java 11
FROM maven:3.8.8-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Etapa 2: Imagen ligera solo con JRE 11
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY --from=build /app/target/apilogin-0.0.1-SNAPSHOT.jar app.jar

# Variables de entorno para la conexion a MySQL externo (configurar en EasyPanel)
ENV DB_URL=jdbc:mysql://HOST_EXTERNO:3306/dbcobranza
ENV DB_USERNAME=root
ENV DB_PASSWORD=changeme
ENV SERVER_PORT=8383

EXPOSE 8383

# Usar shell form para que las variables de entorno se expandan correctamente
ENTRYPOINT java -jar app.jar \
  --spring.datasource.url=${DB_URL} \
  --spring.datasource.username=${DB_USERNAME} \
  --spring.datasource.password=${DB_PASSWORD} \
  --server.port=${SERVER_PORT}
