FROM openjdk:14-alpine
ADD target/hakimsSuperServer-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java","-jar","/app.jar"]