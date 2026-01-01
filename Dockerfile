# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /build/target/srk_app-0.0.1-SNAPSHOT.jar app.jar

# SSL CA
COPY src/main/resources/ca.pem /app/ca.pem
COPY create-truststore.sh /app/create-truststore.sh
RUN chmod +x /app/create-truststore.sh && /app/create-truststore.sh

EXPOSE 8000
ENTRYPOINT ["java","-Djavax.net.ssl.trustStore=/app/truststore.jks","-Djavax.net.ssl.trustStorePassword=changeit","-jar","app.jar"]
