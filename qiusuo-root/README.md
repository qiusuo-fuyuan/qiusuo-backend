# How to build  qiusuo-messagingsrv
Step 1: first build rabbitmq docker image
cd qiusuo-root/config/dev/rabbitmq&docker-compose build

Step 2:
docker network create -d bridge dev-qiusuo-cluster --subnet=172.20.0.0/16


Step 3 Starting all the docker containers
cd config/dev&docker-compose up

# How to build the qiusuo-community project

Step 1: install lombok annotation processor plugin in intellij\
Step 2: enable annotation processing in Intellij \


# Start the community-service from command line
git clone https://github.com/qiusuo-fuyuan/qiusuo-backend.git& 
git checkout develop\
cd qiusuo-gatewaysvr&gradle bootRun\
cd qiusuo-configsvr&run gradle bootRun

You can either start qiusuo-communityservice or using gradle or using
intellij 



# Migrating to Spring WebFlux
(1) Use netty has web server\
(2) For graphql, there is already examples online\
(3) For mysql, we will use the R2DBC for now(not sure how many problems it has)


