#!/bin/bash

#Preparing environment
echo Building truck application
cd ../truck
mvn clean package -DskipTests

# Building the docker image
echo Building truck docker
docker build -t delivery-drone/truck .

# Building the docker image
echo Building drone docker
cd ../drone
docker build -t delivery-drone/drone .

# Building the docker image
echo Building drone docker
cd ../warehouse
docker build -t delivery-drone/warehouse .