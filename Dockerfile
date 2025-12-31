# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy application JAR
COPY --from=build /build/target/*.jar app.jar

# Copy CA from resources
COPY src/main/resources/ca.pem /app/ca.pem

# Copy and run truststore script
COPY create-truststore.sh /app/create-truststore.sh
RUN chmod +x /app/create-truststore.sh
RUN /app/create-truststore.sh

# JVM SSL config
ENV JAVA_OPTS="\
-Djavax.net.ssl.trustStore=/app/truststore.jks \
-Djavax.net.ssl.trustStorePassword=changeit"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
