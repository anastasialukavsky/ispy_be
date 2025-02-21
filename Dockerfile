FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Copy Gradle Wrapper
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

COPY . .

# Run jOOQ Code Generation (No Database Check in Build Step)
RUN ./gradlew generateJooq

# Build the application inside Docker
RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/ispy-0.0.1-SNAPSHOT.jar app.jar

# Add wait-for-it.sh for waiting on Postgres at runtime
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "app.jar"]
