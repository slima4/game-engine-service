FROM amazoncorretto:17

ARG VERSION=1.0.0

ADD core/target/core-$VERSION.jar game-engine-service.jar

ENTRYPOINT ["java", "-jar", "game-engine-service.jar"]
EXPOSE 8080
