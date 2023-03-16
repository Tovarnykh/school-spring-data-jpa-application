FROM openjdk
WORKDIR /app
COPY --from=build /app/target/*.jar school.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "school.jar"]