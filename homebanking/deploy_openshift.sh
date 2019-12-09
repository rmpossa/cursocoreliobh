#Primeiro, deve-se logar no oc (oc login). Depois, executar os comandos abaixo
#Criar projeto homebanking no openshift
oc new-project homebanking
# Deploy de cada um dos microsserviços no openshift (usa plugin Fabric8)
cd api-gateway
mvn clean package fabric8:deploy -DskipTests -Popenshift
cd ../coaf
mvn clean package fabric8:deploy -DskipTests -Popenshift
cd ../cartao-credito
mvn clean package fabric8:deploy -DskipTests -Popenshift
cd ../homebanking-server
mvn clean package fabric8:deploy -DskipTests -Popenshift

# Deploy do nanosserviço no openshift
cd ../reajustar-boleto
mvn clean package -Pnative -Dnative-image.docker-build=true -DskipTests=true
oc new-build quay.io/quarkus/ubi-quarkus-native-binary-s2i:19.2.0 --binary --name=reajustar-boleto
oc start-build reajustar-boleto --from-file target/*-runner --follow
oc new-app reajustar-boleto
oc expose svc/reajustar-boleto

# para testar o nanosserviço, acesse :
# http://reajustar-boleto-homebanking.127.0.0.1.nip.io/boleto?valorOriginal=1000.0&dataPagamento=12/14/2019&dataVencimento=11/14/2019
# deve retornar 1030.0, 30 reais a mais do que o valor original, pois houve o pagamento foi feito com um mês de atraso
