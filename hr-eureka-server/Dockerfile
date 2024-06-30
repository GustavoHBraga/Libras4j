FROM openjdk:17
VOLUME /tmp
ADD ./target/hr-server.jar hr-server.jar
ENTRYPOINT ["java", "-jar","/hr-server.jar"]

EXPOSE 8761