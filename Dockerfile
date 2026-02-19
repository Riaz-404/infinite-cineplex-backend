# Step 1: Build stage
FROM gradle:8.10-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle ./gradle

RUN ./gradlew dependencies --no-daemon || true

COPY src ./src
RUN ./gradlew clean bootJar --no-daemon

# Step 2: Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

# Render provides PORT dynamically
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
