# How to build  qiusuo-messagingsrv
Step 1:Go to folder qiusuo-root/config/dev/rabbitmq\
run docker-compose build to build the rabbitmq docker image

Step 2:
docker network create -d bridge dev-qiusuo-cluster --subnet=172.20.0.0/16


Step 3 cd config/dev, run docker-compose up

# How to build the qiusuo-community project

Step 1: install lombok annotation processor plugin in intellij\
Step 2: enable annotation processing in Intellij \


# Start the community-service from command line
git clone https://github.com/qiusuo-fuyuan/qiusuo-backend.git& 
git checkout develop\

Currently we are migrating all the configurations to qiusuo-configsvr.
You can start configsvr first by running "gradle bootRun" in the qiusuo-configsvr
project first. Then go to qiusuo-communityservice, 
the qiusuo-communityservice will read the config from configsvr during startup


We are also working on the qiusuo-gatewaysvr so that frontend request will all go through the
gatewaysvr.




# Migrating to Spring WebFlux
(1) Use netty has web server\
(2) For graphql, there is already examples online\
(3) For mysql, we will use the R2DBC for now(not sure how many problems it has)


