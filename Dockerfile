FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Copy Gradle Wrapper
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# Copy the entire source code
COPY . .

# Run jOOQ Code Generation
RUN ./gradlew generateJooq

RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/ispy-0.0.1-SNAPSHOT.jar app.jar

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "app.jar"]
