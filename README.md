
# InternetComputingProjectUsingDocker

Description:

###  System requirements
1. java
2. maven
3. eclipse
4. docker

### How to install and run through eclipse

		1. import project as existing maven project
		2. Right click on **InternetApplication.class** and run as Java Application. 
		3. If the application fails to start since the port is already in use, then either kill the process running on 8080 or change the port number through application.properties file in main/java/resources folder.
### How to run through docker cli		
1. Open terminal and cd into this project
2. run **```mvn clean install```** 
3. then run **```docker-compose up -d```**
4. this will run the project on 8080.
5. you can check the created images by running **```docker images```** and also, you can see the container by running **```docker ps```**
6. If you want to bring down the container then run **```docker-compose down```**
7. Run **```docker rmi -f <Image-Id>```** to delete any existing image.

**Example**:

localhost:8080/customers : `[{"id":"1","name":"Alan","order":[{"id":10,"name":"Shoes"},{"id":11,"name":"Shampoo"}]},{"id":"2","name":"Elina","order":[{"id":20,"name":"Shoes"},{"id":21,"name":"Shampoo"}]}]` 

localhost:8080/customers/1 : `{"id":"1","name":"Alan","order":[{"id":10,"name":"Shoes"},{"id":11,"name":"Shampoo"}]}`

localhost:8080/customers/1/orders : `[{"id":10,"name":"Shoes"},{"id":11,"name":"Shampoo"}]`

 localhost:8080/customers/1/orders/10 : `{"id":10,"name":"Shoes"}`


**References**:
1. How to install Eclipse: [https://wiki.eclipse.org/Eclipse/Installation](https://wiki.eclipse.org/Eclipse/Installation)
2. How to install Jdk: brew cask install java
3. How to install docker: [https://docs.docker.com/docker-for-mac/install/](https://docs.docker.com/docker-for-mac/install/)
4. How to install maven: brew install maven
