#FROM openjdk:21
FROM eclipse-temurin:21-jdk
COPY ./target/seMethods.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods.jar", "db:3306", "10000"]
