#!/bin/bash

#Preparing environment
cd ../../
mvn clean package -DskipTests
cp ./target/si5-sacc-td1.jar docker/webapp/.

# Building the docker image
cd docker/webapp/
docker build -t si5-sacc/td1 .

# Cleaning up the environment
#rm -rf si5-sacc-td1.jar