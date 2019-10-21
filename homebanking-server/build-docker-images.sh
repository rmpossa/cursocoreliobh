# executar os comandos abaixo, um por vez, na pasta do projeto homebanking-server
docker build --tag=alpine-java:base --rm=true .
docker build --file=Dockerfile.maven --tag=maven:base --rm=true .
docker build --file=Dockerfile.app --tag=homebanking-server:latest --rm=true .
# executar o comando abaixo na pasta do projeto coaf
docker build --file=../coaf/Dockerfile.app --tag=coaf-server:latest --rm=true .
# executar o comando abaixo na pasta do projeto cartao de credito
docker build --file=../cartao-credito/Dockerfile.app --tag=cartaocredito-server:latest --rm=true . 
# executar o comando abaixo na pasta do projeto api-geteway
docker build --file=../api-gateway/Dockerfile.app --tag=api-gateway-server:latest --rm=true .
