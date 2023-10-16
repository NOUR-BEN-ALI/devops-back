FROM openjdk:8
EXPOSE 8089
ADD target/achat-1.0.1-SNAPSHOT.jar achat-1.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "achat-1.0.1-SNAPSHOT.jar" ]