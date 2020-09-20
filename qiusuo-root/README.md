# How to build  qiusuo-messagingsrv
Step 1:Go to folder qiusuo-root/config/dev/rabbitmq\
run docker-compose build to build the rabbitmq docker image

Step 2:
docker network create -d bridge dev-qiusuo-cluster --subnet=172.20.0.0/16

  
Step 3 cd config/dev, run docker-compose up

# How to build the qiusuo-community project

Step 1: install lombok annotation processor plugin in intellij\
Step 2: enable the annotation processor in Intellij plugin\


# Start the community-service from command line
cd qiusuo-communityservice 

gradle bootrun



# Migrating to Spring WebFlux
(1) Use netty has web server\
(2) For graphql, there is already examples online\
(3) For mysql, we could use a custom thread pool for the db operations.Then 
we will not block the Netty event loop thread. 


