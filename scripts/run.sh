#!/bin/sh

export JAVA_TOOL_OPTIONS="-Xmx2048M"
export SPRING_PROFILES_ACTIVE="pluto"

java -jar app/app.jar


