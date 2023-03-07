FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /school-spring-data-jpa-cli-application
COPY . .
RUN mvn clean package

FROM openjdk:17-oracle
WORKDIR /school-spring-data-jpa-cli-application
COPY --from=build /school-spring-data-jpa-cli-application/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]