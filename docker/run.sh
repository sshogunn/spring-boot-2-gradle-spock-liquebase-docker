docker-compose down
docker kill $(docker ps | grep 'template' | awk {'print $1'})
docker rmi $(docker images | grep 'template' | awk {'print $3'})

cd .. && gradle clean build docker
cd docker && docker-compose up
