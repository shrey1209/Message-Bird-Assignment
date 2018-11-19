# Message-Bird-Assignment
Assignment from Message Bird

URLs:
Homer Simpson: http://a993462e0ec0a11e8974302c61b6039e-569292508.eu-west-2.elb.amazonaws.com/homersimpson
Covilha Time Zone: http://a993462e0ec0a11e8974302c61b6039e-569292508.eu-west-2.elb.amazonaws.com/covilha

Tools and Technologies:
1. Java
2. Spring Boot
3. Apache Tomcat
4. Micrometer
5. Prometheus
6. Docker
7. AWS Cloud
8. Kubernetes

About Applicatoin:

1. Application developed in Spring Boot 2.0.2.RELEASE and Apache Tomcat 8.5.31, Java version 1.8
2. Used Spring-Boot-Maven-Plugin 1.2.5.RELEASE for Packaging
3. Integrated Micrometer & Prometheus v1.10

Code contains 4 classes viz: 
  1. Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarterMessageBirdAPI.java: to initialize Spring Boot
  2. Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarter/homer/HomerSimpson.java: for Homer Simpson Picture
  3. Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarter/timezone/TimeConvertor.java: to Convert the current time into Covilha Time
  4. Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarter/timezone/TimeZone.java: to Expose /covilha endpoint

There is one more class Message-Bird-Assignment/src/main/java/io/messagebird/configuration/MicrometerConfiguration.java for Prometheus Configuration.

Also there is one .jsp file Message-Bird-Assignment/src/main/resources/META-INF/resources/WEB-INF/jsp/homer.jsp for HomerSimpson endpoint.

Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarter/homer/HomerSimpson.java:
This is a very easy class where homer.jsp is getting called and the request is mapped with /homersimpson

Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarter/timezone/TimeConvertor.java:
This class converts the current time into covilha time zone using Java TimeZone, Calender, Date packages. Basically, in this class, using TimeZone tz = TimeZone.getDefault(); we first get the current time zone and with TimeZone tzInAmerica = TimeZone.getTimeZone("Europe/Lisbon"); we convert current time to covilha time.

Message-Bird-Assignment/src/main/java/io/messagebird/springbootstarter/timezone/TimeZone.java:
This class creates an object of TimeConvertor.java and get the current time in covilha then it get mapped to /covilha endpoint.

Prometheus Set up to capture request count to /homersimpson and /covilha:

1. Message-Bird-Assignment/src/main/java/io/messagebird/configuration/MicrometerConfiguration.java:
This class is for Micrometer Configuration where we are setting up a common variable application as "MessageBird_Assessment_App"

2. Used timed annotation to set specific names for the graphs in Prometheus for both the URLs.
   //For /homersimpson
   @Timed(
			value="messagebird.homersimpson.homer",
			histogram=true
	)
  //For /covilha
  @Timed(
			value="messagebird-covilha-time",
			histogram=true
	)
3. Downloaded and Installed prometheus in a Ubuntu VM and configured prometheus.yml as below
   #Global configurations
global:
  scrape_interval:     5s # Set the scrape interval to every 5 seconds.
  evaluation_interval: 5s # Evaluate rules every 5 seconds.

scrape_configs:
  - job_name: 'MessageBird'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['a993462e0ec0a11e8974302c61b6039e-569292508.eu-west-2.elb.amazonaws.com']
  - job_name: 'node_exporter'
    scrape_interval: 5s
    static_configs:
      - targets: ['a993462e0ec0a11e8974302c61b6039e-569292508.eu-west-2.elb.amazonaws.com']

where "a993462e0ec0a11e8974302c61b6039e-569292508.eu-west-2.elb.amazonaws.com" is the ELB in AWS configured for kubernetes cluster.

Docker Configuration:
1. Installed Docker in Ubuntu machine
2. Started Docker with command: sudo service docker start
3. Build the Docker image with command docker build -f Dockerfile -t messagebird .
4. login to docker using docker login command
5. Creating docker tags for docker hub with command docker tag <imageId> dockerhubusername/<image_Name>:<tag>
6. Pushing Image with command: docker push dockerhubusername/<image_Name>:<tag>
  
Setting up Kubernetes Cluster:

Tools Used:
1. kops
2. Terraform

Kubernetes Cluster Creation on AWS using KOPS:
1. Install Python on a Ubuntu EC2 instance
2. Install PIP
3. Install AWSCLI using pip
4. Install kubectl
5. Install KOPS
6. Install Terraform
7. Purchase a DNS Name, in this case I've used awscloudguru.tk
8. Create Hosted Zone in AWS
9. Update the Name Servers in the DNS Provider website for the purchased DNS
10. Create a ssh public key using AWS CLI using command : aws ec2 create-key-pair --key-name k8s-key | jq -r '.KeyMaterial' >k8s-key.pem
11. Create S3 bucket.
12. Now, create K8S cluster using KOPS in AWS with below command
    kops create cluster \
  --name=kops.awscloudguru.tk \
  --state=s3://kopscluster.k8s \
  --authorization RBAC \
  --zones=eu-west-2c \
  --node-count=2 \
  --node-size=t2.micro \
  --master-size=t2.micro \
  --master-count=1 \
  --dns-zone=awscloudguru.tk \
  --out=messagebird_terraform \
  --target=terraform \
  --ssh-public-key=/home/ubuntu/keys/k8s-key.pub
13. As soon as execution of the above command is completed, a kubernetes.tf file got created in the folder specified in --out tag.
14. Go to that folder and ru terraform init and terraform apply command to create the k8s cluster.
15. Once the cluster creation is complete with above commands, 2 node machines and 1 master machines will be created in the AWS Console. Along with machines, there will be a lot more things also getting created in the AWS line security groups, VPC, Subnets, Network Interface, IGW, etc etc etc....
16. Now the Kubernetes CLuster is ready in AWS. Time to Deploy out Spring Boot application.

Deploy Spring Boot Application in AWS K8S Cluster using kubectl:

1. Create deployment using command: kubectl create -f deployment.yml
2. Create service using command: kubectl create -f service.yml

* Both the files are present in the repository

3. Open the newly created port in security group for inbound rules

***** Application Ready to Go *****

Setting up Prometheus:

1. Create a EC2 instance
2. Download and Install Prometheus in this instance.
3. Create Prometheus.yml as above.
4. Start Prometheus with this .yml file as config
5. Hit http://52.56.99.21:9090 and Monitor the application.

  
