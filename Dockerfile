FROM openjdk:8-jdk-alpine
MAINTAINER Ayorinde
COPY target/kiddiesave-0.0.1-SNAPSHOT.jar kiddiesave-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/kiddiesave-0.0.1-SNAPSHOT.jar"]