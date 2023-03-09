FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM flyway/flyway:7.15-alpine
COPY --from=build /app/target/*.jar /flyway/sql/school.jar
COPY db/migration /flyway/sql
CMD ["-url=jdbc:postgresql://school_db:5432/school", "-user=fox", "-password=foxminded", "migrate"]

FROM openjdk
WORKDIR /app
COPY --from=build /app/target/*.jar school.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "school.jar"]