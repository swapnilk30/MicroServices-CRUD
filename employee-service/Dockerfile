# Use Java 17 runtime
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy Spring Boot JAR
COPY target/employee-service-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 9090

# Run Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]