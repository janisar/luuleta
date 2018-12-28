FROM openjdk:11.0-oracle
VOLUME /tmp
ARG JAR_FILE=build/libs/learningsession-0.1.0.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["/usr/bin/java"]

EXPOSE 8080

CMD ["-jar", "app.jar"]
