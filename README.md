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
### Docker
Build the jar locally with ``mvn package``.

Then get the container running with ``docker-compose -f deployment/docker-compose.yml up``

In a production environment, obviously change ``config.env`` to use private passwords.

### Kubernetes
Build the docker image locally (or push it to a private repository): ``docker build -f deployment/Dockerfile -t bpol_advent .``

Then apply both configs, with the secrets first (of which you also changed the passwords): ``kubectl apply -f deployment/kubernetes-secret.yml && kubectl apply -f deployment/kubernetes.yml``.

If you wish to use the ingress deployment yourself, it would probably be best to alter domain name and certificate resolvers to the ones you wish to use in your environment.
