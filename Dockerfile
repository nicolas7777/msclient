FROM openjdk:11
VOLUME /tmp
EXPOSE 8889
ADD ./target/msPersonal-0.0.1.jar msclient.jar
ENTRYPOINT ["java","-jar","/msclient.jar"]
