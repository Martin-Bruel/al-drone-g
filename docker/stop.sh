#!/bin/bash

echo "<<" Cleaning the drone projet ">>"
echo
docker-compose -f ./docker-compose.yml down --volumes
echo
echo Done.
