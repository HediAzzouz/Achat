FROM openjdk
EXPOSE 8089
ADD target/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java", "-jar", "/timesheet-devops-1.0.jar"]