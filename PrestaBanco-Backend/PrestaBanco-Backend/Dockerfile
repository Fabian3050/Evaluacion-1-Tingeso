FROM openjdk:20
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} PrestaBanco-Backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","./PrestaBanco-Backend-jar"]