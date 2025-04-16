#!/bin/sh

mkdir -p ./app
mvn package --no-transfer-progress -DskipTests
cp -fv target/*.jar ./app/app.jar


