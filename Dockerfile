FROM openjdk:8
EXPOSE 8089
COPY target/achat-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
