# sample-scala-rest
Sample application Spring-boot - data-rest - scala

## Architecture

## License
This library is licensed under the Apache License, Version 2.0.

## Swagger UI
http://{url_project}/swagger-ui.html#/

Example: http://localhost:8080/swagger-ui.html#/

## Starting



## Docker deployment

### Configurations

By default the plugin will try to connect to docker on localhost:2375. Set the DOCKER_HOST environment variable to connect elsewhere.

    DOCKER_HOST=tcp://<host>:2375

Other docker-standard environment variables are honored too such as TLS and certificates.

### Usage

You can build an image with the above configurations by running this command.

    mvn clean package docker:build

To push the image you just built to the registry, specify the pushImage flag.

    mvn clean package docker:build -DpushImage

To push only specific tags of the image to the registry, specify the pushImageTag flag.

    mvn clean package docker:build -DpushImageTag

To create instance on docker machine (need environment variables)

    docker create  --name sample-scala-mongo-rest -t -p 8080:8080 florentpeyron/sample-scala-mongo-rest
