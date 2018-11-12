FROM openjdk:12-jdk
ADD target/Message-Bird-Assignment-0.0.1-SNAPSHOT.jar /home/ec2-user/docker_file/Message-Bird-Assignment-0.0.1-SNAPSHOT.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","Message-Bird-Assignment-0.0.1-SNAPSHOT.jar"]