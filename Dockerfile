FROM openjdk:12-jdk
ADD target/Message-Bird-Assignment-0.0.1-SNAPSHOT.jar message-bird-assignment.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","message-bird-assignment.jar"]