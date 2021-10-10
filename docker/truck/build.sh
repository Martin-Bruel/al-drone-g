#!/bin/bash

#Preparing environment
cd ../../truck
mvn clean package
cd ..
cp ./truck/target/truck.jar docker/truck/.

# Building the docker image
cd docker/truck/
docker build -t delivery-drone/truck .

# Cleaning up the environment
#rm -rf si5-sacc-td1.jar