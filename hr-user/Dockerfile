FROM openjdk:17
VOLUME /tmp
ADD ./target/hr-user.jar hr-user.jar
ENTRYPOINT ["java", "-jar","/hr-user.jar"]
EXPOSE 7777
