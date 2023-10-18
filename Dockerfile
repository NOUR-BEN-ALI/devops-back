FROM openjdk:8
EXPOSE 8089
ADD target/javaapp.jar javaapp.jar
ENTRYPOINT ["java","-jar","/javaapp.jar"]