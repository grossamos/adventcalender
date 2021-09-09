FROM openjdk:16-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/questions.json /src/main/resources/questions.json
ENTRYPOINT ["java","-jar","/app.jar"]
