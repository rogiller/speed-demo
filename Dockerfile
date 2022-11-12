FROM openjdk:17-jdk-slim-buster

VOLUME /tmp

EXPOSE 8090

# Set Spring Boot JAR file location
ARG JAR_FILE=build/libs/*.jar

# Add Spring Boot JAR to the container
ADD ${JAR_FILE} app.jar

# Command for Launching the Spring Boot JAR
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
