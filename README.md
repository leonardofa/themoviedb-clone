

![The MovieDB - CLONE](https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg)


# The MovieDB - CLONE
🎥 [BASE DE EXEMPLO](https://developers.themoviedb.org/3) 🇧🇷

### 1. Requisitos a serem buscados:
* RESTfull
* Spring Boot
* Testes

### 2. Techs e stacks a serem usadas:
* Banco de Dev e Teste: H2 (console liberado para navegar. Vide item 6.1)
* Banco futuro para staging: Mysql
* Flyway para migrations
* Lombok: menos verbosidade das classes
* Swagger para documentação dos endpoints
* Testes unitários e ou de integração com banco (Junit e Mockito)
* Repositório Git
* Comenatários no código para para complementar entendimento

### 3. Parametros da aplicação (Enviroments w/ SpringBoot):

### 4. Implementações (dominíos):


### 5. Docker:
 1. Para facilitar (ou não) pode ser usado o [docker](https://docs.docker.com/get-docker/) para criar um banco:
`docker run --name mysql-moviedb-clone -p 3306:3306 -e MYSQL_ROOT_PASSWORD=docker -d mysql

### 6. Outros:
1. Configurar e navegar pelo console do h2:
