FROM openjdk:8-jdk-alpine

LABEL maintainer="y.lei2@student.vu.nl"

ARG JAR_FILE=target/backend-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","/app.jar"]