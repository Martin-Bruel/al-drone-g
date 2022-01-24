#!/bin/bash

echo How many drone do you want to launch?
read nbDrone

reNumber='^[0-9]+$'
if ! [[ $nbDrone =~ $reNumber ]] || [[ $nbDrone -lt 1 ]] ; then
    echo "error: number invalid" >&2; exit 1
elif [[ $nbDrone -gt 10 ]] ; then
    echo "Its a lot of drones, are you sure? (write \"yes\" if sure)"
    read response
    if ! [[ $response == "yes" ]] ; then
        echo "Okay, we stop here" >&2; exit 1
    fi
fi

echo stop previous drones
./stopAll.sh
echo Start $nbDrone drones

droneName="drone_"
portStart=8090

export APP_ENV="multiDrone"

cd ../dronev2

for (( i=1; i<=$nbDrone; i++))
do
    export DRONE_PORT=$portStart
    export DRONE_NAME="$droneName$i"

    logName="log_$DRONE_NAME.txt"
    npm run start &> ../multipleDrones/logs/$logName &

    ((portStart++))
    sleep 0.08
done