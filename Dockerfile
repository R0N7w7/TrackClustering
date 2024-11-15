FROM openjdk:21-jdk-slim

ARG JAR_FILE=build/libs/mi-aplicacion.jar

COPY ${JAR_FILE} app.jar

EXPOSE 4982

ENTRYPOINT ["java", "-jar", "/app.jar"]
