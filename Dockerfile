FROM openjdk:17-jdk-slim-buster

ENV APP_HOME=/app
ENV APP_JAR=SberCoffee-0.0.1-SNAPSHOT.jar

WORKDIR $APP_HOME

COPY target/$APP_JAR $APP_HOME/

ENV SERVER_PORT=8080

EXPOSE $SERVER_PORT

CMD ["java", "-jar", "SberCoffee-0.0.1-SNAPSHOT.jar"]
