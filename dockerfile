# Steg 1: Bygg appen med Gradle
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Kopiera Gradle-filer
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Ge gradlew execute-rättigheter
RUN chmod +x gradlew

# Kopiera source code
COPY src ./src

# Bygg projektet
RUN ./gradlew clean bootJar --no-daemon

# Steg 2: Kör appen
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]