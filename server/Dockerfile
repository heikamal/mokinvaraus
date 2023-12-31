FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

COPY target/*.jar app.jar

ENV SQL_HOST db
ENV SQL_PORT 1433
ENV SQL_DB cabindb
ENV SQL_USER SA
ENV SQL_PASSWORD password123!

ENTRYPOINT ["java","-jar","/app.jar"]