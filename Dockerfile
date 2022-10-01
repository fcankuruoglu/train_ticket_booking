FROM openjdk:17
COPY /target .
ENTRYPOINT ["java", "-jar", "train-ticket-booking-0.0.1-SNAPSHOT.jar"]