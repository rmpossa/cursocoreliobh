cd homebanking-server
mvn clean package -DskipTests
cd ../coaf
mvn clean package -DskipTests
cd ../cartao-credito
mvn clean package -DskipTests
cd ../api-gateway
mvn clean package -DskipTests
