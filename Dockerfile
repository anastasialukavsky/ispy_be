# Stage I: Build the application
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Copy Gradle Wrapper
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# Copy the entire source code
COPY . .

# Build the application inside Docker
RUN ./gradlew build --no-daemon -x test

# Stage II: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/ispy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
