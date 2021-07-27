# BPol Adventskalender

## Description
This Project represents an internal advent calendar of the German Bundespolizei. 
It's here to spread cheer and christmas joy, therefore be sure to have fun :D.

## Development Guide
First off get the development database running by calling: ``docker-compose -f deployment/docker-compose-dev.yml up -d``.

Then make sure you have all envfiles while running the spring boot application. 
This can be done through run configurations in your respective IDE or on unix systems by calling: ``export $(cat deployment/config.env | xargs)``.
All .env files can be found under ``deployment``.

## Deployment Guide
Build the jar locally with ``mvn package``.

Then get the container running with ``docker-compose -f deployment/docker-compose.yml up``