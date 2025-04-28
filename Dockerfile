FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/flywell-0.0.1-SNAPSHOT.jar flywell.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "flywell.jar"]

