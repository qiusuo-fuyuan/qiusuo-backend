# How to build qiusuo-messagingsrv
Step 1:Go to folder qiusuo-root/config/dev/rabbitmq. 
run docker-compose build to build the rabbitmq docker image

Step 2:
docker network create -d bridge dev-qiusuo-cluster --subnet=172.20.0.0/16\

  
Step 3 cd config/dev, run docker-compose up\

# How to build the qiusuo-community project

Step 1: install lombok annotation processor plugin in intellij
Step 2: enable the annotation processor in Intellij plugin
Step 3: right click qiusuo-community project, select 
maven generate-sources and update target folders

Step 4: Now the app should be able to start


