FROM amazoncorretto:17
COPY target/Alpe-0.0.1-SNAPSHOT.jar alpe.jar
CMD ["java", "-jar", "/alpe.jar"]