
> Prerequisite: docker installation on your machine to be able to run application and tests! (please see https://docs.docker.com/get-docker/)

We already have a repository of hotels to store hotels to a Postgres Database.

To make our application usable to others we have implemented a REST API that provides several endpoints.
To see all our available Endpoints and to  be able to call them in a convenient way we also added Swagger.
This allows us to open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) and see the SwaggerUI with always up-to-date documentation of our endpoints as well as the ability to execute http requests from the UI against them.

### How to Start application
```shell
docker compose up
./gradlew bootRun
```

### How to execute all tests
```shell
./gradlew build
```

### Look into Database
Since PgAdmin will be started as part of the docker compose setup you should be able to open PgAdmin here http://localhost:5050/ .

## Exercise:
Store Rooms into database. A Room should be connected to certain hotel. A Hotel can have multiple Rooms.
Also implement `RoomController`.