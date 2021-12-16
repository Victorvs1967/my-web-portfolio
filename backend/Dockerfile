FROM openjdk:17.0.1-slim-buster

ADD . /app
WORKDIR /app

RUN ./mvnw clean package -DskipTests
RUN mv target/backend-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]