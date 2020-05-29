# Qiusuo Community Chat Server
## How to run
First start all the background services, cassandra, rabbitmq, redis, mysql\
Step 1 docker network create -d bridge dev-qiusuo-cluster --subnet=172.20.0.0/16\
Step 2 cd config/dev, run docker-compose up\

Last Step 
Start qiusuo-community in intellij


