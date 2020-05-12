## Continuous Delivery process for On-premise applications and infrastructure. Stepwise explanation.
# Two simple microservices (HelloDevops and HelloWorld) - Developed in springboot application and nginx as web server.
# Created docker files to build multiple docker images 
# Jenkins file works as pipeline as code to create jenkins job with multiple stages such as build, deploy to dev, test in dev, deploy to test, test in test, deploy to prod
# Jenkins picks the code from the github url to build.
# Jenkins file initiates the docker commands to create containers from the docker images and starts deploying the code to different regions as specified.
# Once the code is deployed to dev region successfully, the tests are triggered to run on the respective docker dev/test containers
# After the tests are passed, jenkins triggers the ansible playbook to build the configuration of the prod environment to deploy the microservices in the respective environment provisioned through the config, deploy yml files.
# There is no monitoring system attached as there are no logging code written. 


## Docker Stuff

Create the user-defined bridge network (so we get embedded DNS):
```
docker network create --driver bridge hwms_nw
```

Build the machines:
```
docker build -t hdms .
docker build -t hdms_sbweb docker-images/hdms_sbweb

docker build -t hwms .
docker build -t hwms_sbweb docker-images/hwms_sbweb
```
Start the Spark Framework web tier:
```
docker run --detach --network=hdms_nw --hostname hdmsweb1 --name hdmsweb1 hdms_web
docker run --detach --network=hdms_nw --hostname hdmsweb2 --name hdmsweb2 hdms_web

docker run --detach --network=hwms_nw --hostname hwmsweb1 --name hwmsweb1 hwms_web
docker run --detach --network=hwms_nw --hostname hwmsweb2 --name hwmsweb2 hwms_web
```

Start the Spring Boot web tier:
```
docker run --detach --network=hwms_nw --hostname hdmssbweb1 --name hdmssbweb1 hdms_sbweb
docker run --detach --network=hwms_nw --hostname hdmssbweb2 --name hdmssbweb2 hdms_sbweb

docker run --detach --network=hwms_nw --hostname hwmssbweb1 --name hwmssbweb1 hwms_sbweb
docker run --detach --network=hwms_nw --hostname hwmssbweb2 --name hwmssbweb2 hwms_sbweb
```

Start the NGINX load balancer:
```
docker run --detach --network=hdms_nw --hostname hdmslb --name hdmslb --publish 8080:80 --volume /home/docker/HelloDevopsMicroservice/nginx.conf.d:/etc/nginx/conf.d:ro nginx
```
docker run --detach --network=hwms_nw --hostname hwmslb --name hwmslb --publish 8080:80 --volume /home/docker/HelloWorldMicroservice/nginx.conf.d:/etc/nginx/conf.d:ro nginx

Test:
```
curl http://localhost:8080/service/users
```

Access the [Browser UI](http://localhost:8080/) via NGINX.
