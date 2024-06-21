#FROM openjdk:17
#EXPOSE 8080
#COPY Automation-Framework-0.0.1-SNAPSHOT.jar /app/Automation-Framework-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "/app/Automation-Framework-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.4-openjdk-17-slim as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package
FROM openjdk:17
EXPOSE 8080
WORKDIR /automation-framework
COPY --from=maven target/CRMVTiger.ApplicationFramwork-0.0.1-SNAPSHOT.jar /app/CRMVTiger.ApplicationFramwork-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/CRMVTiger.ApplicationFramwork-0.0.1-SNAPSHOT.jar"]