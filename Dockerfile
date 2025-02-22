FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

COPY . .

ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD

ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD

RUN ./gradlew dependencies --no-daemon
RUN ./gradlew clean jooqGenerate --no-daemon
RUN mkdir -p src/main/generated && cp -r build/generated-sources/jooq src/main/generated
RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD

COPY --from=builder /app/build/libs/ispy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
