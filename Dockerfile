#FROM adoptopenjdk/openjdk8:jre8u292-b10-alpine

FROM adoptopenjdk/openjdk11:jre-11.0.10_9

#FROM adoptopenjdk/openjdk16:jre-16.0.1_9-alpine

VOLUME /tmp

# Spring Boot port 8500 available to the host
EXPOSE 8080

# Set Spring Boot JAR file location
ARG JAR_FILE=build/libs/*.jar

# Add Spring Boot JAR to the container
ADD ${JAR_FILE} app.jar

# Command for Launching the Spring Boot JAR
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
