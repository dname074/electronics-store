FROM eclipse-temurin:21
COPY electronics-store-bff/bff-adapter/target/bff-adapter-1.0-SNAPSHOT.jar bff-service.jar
ENTRYPOINT ["java", "-jar", "/bff-service.jar"]