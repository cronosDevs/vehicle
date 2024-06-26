FROM maven:3.8.3-openjdk-8 as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean install package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/vehicle.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
