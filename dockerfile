FROM openjdk:20
MAINTAINER github/gregorylacerda
COPY ./target/AMSList-0.0.1-SNAPSHOT.jar /app/AMSList-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "AMSList-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080