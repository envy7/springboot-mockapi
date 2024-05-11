FROM arm64v8/openjdk:17-ea-16-jdk
MAINTAINER Noel
COPY build/libs/mockapi-0.0.1-SNAPSHOT.jar mockapi.jar
ADD src/db /db
ENTRYPOINT ["java", "-jar", "/mockapi.jar"]