nacos_dir="./nacos-docker"
[ ! -d "nacos_dir" ] && git clone --depth 1 https://github.com/nacos-group/nacos-docker.git
cd nacos-docker
docker-compose -f example/standalone-derby.yaml up