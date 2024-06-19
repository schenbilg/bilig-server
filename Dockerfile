FROM openjdk:21-alpine

COPY target/uberjar/bilig-server.jar /bilig-server/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/bilig-server/app.jar"]
