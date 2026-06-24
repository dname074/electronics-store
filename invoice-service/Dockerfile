FROM eclipse-temurin:21
COPY invoice-service/invoice-adapter/target/invoice-adapter-1.0-SNAPSHOT.jar invoice-service.jar
ENTRYPOINT ["java", "-jar", "/invoice-service.jar"]