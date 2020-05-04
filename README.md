# Loja Virtual - Capitani
API REST in SpringBoot 2.2.6 with H2 Database

## Instructions for online use
Address in Heroku<br/>
https://api-lojavirtual-capitani.herokuapp.com/swagger-ui.html
<br/><br/>
For authentication, enter the "/auth" path and use the default login (user) and password (123456) for this test only. If correct, please write down the Token.
<br/>
In the request header, you must inform the "authorization" type "Bearer" and inform the Token.
<br/>
In all requests you must use the Token.

## Instructions for local use

Mac or Linux with maven portable in project<br/>
```
./mvnw spring-boot:run
```

Docker local

Create package for docker use
```
./mvnw package
```
Create image docker
```
docker build -t api/loja-virtual-capitani .
```
Run image docker 
```
docker run -p 8080:8080 api/loja-virtual-capitani
```

Access address: http://localhost:8080/swagger-ui.html