FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

#Workspace
WORKDIR /usr/share/ayellapu

# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs

# ADD suite files
ADD automationpractice.xml              automationpractice.xml
ADD webappsecurity.xml                  webappsecurity.xml

# ADD health check script
ADD healthcheck.sh                      healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh
