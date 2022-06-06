FROM openjdk:8
ADD target/atm-1.0-SNAPSHOT.jar atm-application.jar
EXPOSE 7676
ENTRYPOINT ["java", "-jar", "atm-application.jar"]