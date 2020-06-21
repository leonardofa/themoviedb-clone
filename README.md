
# The MovieDB - CLONE
🎥 [BASE DE EXEMPLO](https://developers.themoviedb.org/3) 🇧🇷

### 1. Requisitos a serem buscados:
* RESTfull
* Spring Boot
* Testes

### 2. Techs e stacks a serem usadas:
* Repositório Git
* Banco de Dev e Teste: H2 (console H2 habilitado em Vide item 4.1)
* Flyway para migrations
* Lombok para diminuir verbosidade das classes
* Testes unitários e ou de integração com banco (JUnit e Mockito)
* Valores dinâmicos para testes com [java-faker](https://java-faker.herokuapp.com/)
* Swagger para documentação dos endpoints em http://localhost:8080/swagger-ui.html
* Handlers para validação e erros de negocio (Básico)
* Rest Client com [insomnia](https://insomnia.rest/download/)
* ~~Autenticação JWT~~

### 3. Implementações de testes:
| Domain  | feature | teste|
|--|--|--|
| Usuario | incluir |deveValidarCamposUsuarioCadastro|
| Usuario | incluir |deveCadastrarNovoUsuario|
| Usuario | incluir |naoDeveCadastrarUsuarioComEmailJaCadastrado|

### 4. Configurações:
1. Navegar pelo console do h2
**http://localhost:8080/h2-console** 
![Configurar e navegar pelo console do h2](https://raw.githubusercontent.com/leonardofa/themoviedb-clone/master/readme/resource/img/h2-console.png)
