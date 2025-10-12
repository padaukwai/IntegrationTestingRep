FROM openjdk:19
COPY ./target/ClonefromLab02R4forunittesting-1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar","ClonefromLab02R4forunittesting-1-jar-with-dependencies.jar"]
