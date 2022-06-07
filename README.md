# ATM spring boot application
### Mini ATM project with spring boot and apache derby inmemory database exposing rest APIs

Steps/guidelines to start the application
1. git clone https://github.com/vekon/atm.git
2. run 'mvn clean install'
3. run 'mvn spring-boot:run' or 'java -jar target/atm-1.0-SNAPSHOT'
4. Use postman to explore rest api on default port 7676

Steps to run as docker container
1. git clone https://github.com/vekon/atm.git
2. CD /atm
3. docker build -t docker-atm-app .
4. docker run -p 7676:7676 docker-atm-app

