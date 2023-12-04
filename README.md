# FitBuddy

## Requirements
* a local instance of MongoDB (https://www.mongodb.com/docs/manual/installation/);
* An Java IDE [IntelliJ works best];
* Java 17 and JDK 20.0.2^ (https://www.java.com/download/ie_manual.jsp);
* Maven (https://maven.apache.org/download.cgi);
* Docker __[Deployment only]__ (https://docs.docker.com/engine/install/).

## Running
1. Clone or download `master` branch;
2. Update the value of `pring.data.mongodb.uri` on __application.properties__ file to the URI of your local install of mongodb with the collection name at the end. e.g.: __mongodb://localhost:27017/fitbuddy__;
3. Make sure all the dependencies are installed;
4. Run the project through your IDE.

## Deploying
1. Before building make sure that the value of `pring.data.mongodb.uri` on __application.properties__ points to the production database on MongoDB Atlas (https://atlas.mongodb.com);
2. On the Maven tab of your IDE, navigate to the fitbuddy -> Lifecycle item on the tree;
3. Run the `Validate` and `Compile` actions;
4. Generate the Docker Image using the command `docker build --build-arg JAR_FILE=target/*.jar -t jpbeltrame/fitbuddy:latest . ` on the bash or PowerShell on the root folder of the project;
5. push the image to the repository we are using DockerHub (https://hub.docker.com/) using the command `docker push jpbeltrame/fitbuddy:latest`;
6. Run the manual re-deploy on Render (https://www.render.com).