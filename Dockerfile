FROM maven:3.9.4-amazoncorretto-21-debian-bookworm AS MAVEN_BUILD
COPY . /
RUN mvn package

FROM openjdk:21-slim-bookworm
EXPOSE 8080
COPY --from=MAVEN_BUILD /target/movieclub-*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]