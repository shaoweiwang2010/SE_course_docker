# Docker in 4 Steps

Let's learn Docker in 4 Easy Steps. 


- Step 00 - Installing Docker
- Step 01 - A simple Docker use case - Run an existing application
- Step 02 - Playing with Docker - Containers and Images
- Step 03 - Manually creating a docker image
- Step 04 – Push image to docker hub


### Step 00 - Installing Docker

- https://docs.docker.com/install/

### Step 01 - A Simple Docker User Case - Run an existing application


```
docker run --name mysql_5.7 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7 
docker exec -it mysql_5.7 bash
mysql -u root –p
```


#### Traditional Deployment

![Traditional Deployment](images/docker-traditional-deployment.png)

#### Deployment with Docker

![Docker Deployment](images/docker-zz-deployment.png)


### Step 02 - Playing with Docker - Containers and Images

- Image is static
- Container is dynamic

```
  docker ps
  docker container ls
  docker restart mysql_5.7
  docker stop mysql_5.7
  ...
```


### Step 03 - Manually creating a new docker image


```
  docker run --name openjdk_8 -dit -p 5000:5000 openjdk:8-jdk-alpine
  docker container cp .\docker-in-5-steps-todo-rest-api-h2-1.0.0.RELEASE.jar openjdk_8:/tmp
  docker exec openjdk_8 ls tmp
  docker exec openjdk_8 java -jar tmp/docker-in-5-steps-todo-rest-api-h2-1.0.0.RELEASE.jar
  docker container commit --change='CMD exec java -jar /tmp/docker-in-5-steps-todo-rest-api-h2-1.0.0.RELEASE.jar’ openjdk_8 webapp:v1
  docker run --name web_app_v1 -dit -p 5000:5000 webapp:v1


```


### Step 04 – Push image to docker hub

Create an account in docker hub (https://hub.docker.com/)

```
docker login --username=shaoweiwang2020
docker tag webapp:v1 shaoweiwang2020/web_app1:v2 
docker push shaoweiwang2020/web_app1:v2
```

## More reference:
https://docs.docker.com/docker-hub/repos/#:~:text=To%20push%20an%20image%20to,docs%2Fbase%3Atesting%20).
https://stackoverflow.com/questions/41984399/denied-requested-access-to-the-resource-is-denied-docker
Video - https://www.youtube.com/watch?v=Rt5G5Gj7RP0

