FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar RubeberTechCatelog.jar
ENTRYPOINT ["java","-jar","/RubeberTechCatelog.jar"]