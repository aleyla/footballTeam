# Football Team
This API provides CRUD operations of football teams, contracts and players

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

# Running the application locally

From terminal, the docker image must be run as

```shell
cd docker/local
```
 ```shell
 docker-compose up -d
 ```
 
After that, from maven the project must be started as (Active profiles=locale)

```shell
spring-boot:run -Dspring-boot.run.profiles=local
```

For running the tests

```shell
mvn test
```

# Opening Swagger

After running the project, swagger link is: [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html)