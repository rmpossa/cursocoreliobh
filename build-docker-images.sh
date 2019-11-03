# executar os comandos abaixo, um por vez, na pasta do projeto homebanking-server
cd homebanking-server
docker build --tag=alpine-java:base --rm=true .
docker build --file=Dockerfile.maven --tag=maven:base --rm=true .
docker build --file=Dockerfile.app --tag=homebanking-server:latest --rm=true .
# executar o comando abaixo na pasta do projeto coaf
cd ../coaf
docker build --file=Dockerfile.app --tag=coaf:latest --rm=true .
# executar o comando abaixo na pasta do projeto cartao de credito
cd ../cartao-credito
docker build --file=Dockerfile.app --tag=cartao-credito:latest --rm=true . 
# executar o comando abaixo na pasta do projeto api-geteway
cd ../api-gateway
docker build --file=Dockerfile.app --tag=api-gateway:latest --rm=true .
