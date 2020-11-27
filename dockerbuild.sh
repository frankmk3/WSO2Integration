#!/usr/bin/env bash

./gradlew clean build

cp ./build/libs/demo*.jar ./dockercompose/wso2integration/app.jar

docker-compose up -d