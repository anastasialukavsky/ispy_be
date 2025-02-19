FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Copy Gradle Wrapper
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# Copy the entire source code
COPY . .

# Ensure database is up before running jOOQ
RUN echo "Waiting for PostgreSQL to be ready..." && \
    while ! pg_isready -h postgres-db -p 5432 -U $POSTGRES_USER; do sleep 2; done

# Run jOOQ Code Generation
RUN ./gradlew generateJooq

RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/ispy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
