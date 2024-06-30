FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-api-gateway.jar hr-api-gateway.jar
ENTRYPOINT ["java", "-jar","/hr-api-gateway.jar"]

EXPOSE 8765