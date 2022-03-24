# Hotel Booking

> Prerequisite: docker installation on your machine to be able to run application and tests! (please see https://docs.docker.com/get-docker/)

We already have a repository of hotels to store hotels to a Postgres Database.

To make our application usable to others we have implemented a REST API that provides several endpoints.
To see all our available Endpoints and to  be able to call them in a convenient way we also added Swagger.
This allows us to open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) and see the SwaggerUI with always up-to-date documentation of our endpoints as well as the ability to execute http requests from the UI against them.

## How to Start application
```shell
./gradlew bootRun
```

## How to execute all tests
```shell
./gradlew build
```
