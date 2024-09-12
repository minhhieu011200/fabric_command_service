FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar /app/fabric.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/fabric.jar"]