# The MovieDB - CLONE
🎥 [BASE DE EXEMPLO](https://developers.themoviedb.org/3) 🇧🇷

### 1. Requisitos a serem buscados:
* RESTfull
* Spring Boot
* Testes

### 2. Techs e stacks a serem usadas:
* Banco de Dev e Teste: H2 (console H2 habilitado em http://localhost:8080/h2-console. Vide item 4.1)
* Banco futuro para staging: Mysql (criação com docker vide item 4.2)
* Flyway para migrations
* Lombok: menos verbosidade das classes
* Swagger para documentação dos endpoints
* Testes unitários e ou de integração com banco (Junit e Mockito)
* Repositório Git
* Comenatários no código para para complementar entendimento

### 3. Implementações (dominíos):
* Usuário: usuário do api (autenticação jwt)


### 4. Configurações:
1. Configurar e navegar pelo console do h2

![Configurar e navegar pelo console do h2](https://raw.githubusercontent.com/leonardofa/themoviedb-clone/master/readme/resource/img/h2-console.png)

2. Para facilitar pode ser usado (ou não) o [docker](https://docs.docker.com/get-docker/) para criar um banco:
`docker run --name mysql-moviedb-clone -p 3306:3306 -e MYSQL_ROOT_PASSWORD=docker -d mysql`
