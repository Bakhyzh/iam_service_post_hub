FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /workspace

# Copy project files and build with Maven
COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jdk

WORKDIR /srv

# Copy the built jar from the build stage (ensure name matches pom artifact)
COPY --from=build /workspace/target/iam_service-0.0.1-SNAPSHOT.jar /srv/iam_service-0.0.1-SNAPSHOT.jar
COPY docker/iam-service.sh /srv/iam-service.sh

RUN chmod +x /srv/iam-service.sh

EXPOSE 8189

ENTRYPOINT ["/srv/iam-service.sh"]