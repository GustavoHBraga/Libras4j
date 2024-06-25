FROM openjdk:17
VOLUME /tmp
ADD ./target/hr-eureka-server-0.0.1-SNAPSHOT.jar hr-server.jar
ENTRYPOINT ["java", "-jar","/hr-server.jar"]

EXPOSE 8761