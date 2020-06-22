

# The MovieDB - CLONE
🎥 [BASE DE EXEMPLO](https://developers.themoviedb.org/3) 🇧🇷

### 1. Requisitos a serem buscados:
* RESTfull
* Spring Boot
* Testes

### 2. Techs e stacks a serem usadas:
* Repositório Git
* Banco de Dev e Teste. Console H2 habilitado. Item 4.1
* Modelo relacional. Item 4.2
* Flyway para migrations
* Lombok para diminuir verbosidade das classes
* Model Mapper para evitar o boilerplate entre os representation e domain models [java-faker](http://modelmapper.org/)
* Testes unitários e ou de integração com banco (JUnit e Mockito)
* Valores dinâmicos para testes com [java-faker](https://java-faker.herokuapp.com)
* Swagger para documentação dos endpoints em http://localhost:8080/swagger-ui.html
* Handlers para validação e erros de negocio (Básico)
* Rest Client com [insomnia](https://insomnia.rest/download/)
* Script com Docker para iniciar sem abrir o projeto. Item 4.3
* Cobertura de código com Jacoco. Item 4.4

### 3. Implementações de entidades e testes:
| Domain  | feature | teste|
|--|--|--|
| Usuario | cadastro |deveValidarCamposUsuarioCadastro|
| Usuario | cadastro |deveCadastrarNovoUsuario|
| Usuario | cadastro |naoDeveCadastrarUsuarioComEmailJaCadastrado|
| Usuario | atualizacao |deveValidarCamposUsuarioAtualizacao|
| Usuario | atualizacao |deveValidarCamposUsuarioAtualizacao|
| Usuario | atualizacao |deveAtualizarUmUsuario|
| Usuario | busca |deveRecuperarTodosUsuarios|
| Usuario | busca |deveRecuperarSomenteUmUsuario|
| Usuario | busca |deveRetornarErroQuandoBuscarUsuarioInexistente|
| Usuario | exclusao |deveExcluirUmUsuario|
| Usuario | exclusao |naoDeveEscluirUsuarioNaoEncontrado|
| Ator | cadastro |deveValidarCamposAtorCadastro|
| Ator | cadastro |naoDeveCadastraAtorComNomeeDataNascimentoJaCadastrado|
| Ator | cadastro |deveCadastrarNovoAtor|
| Ator | atualizacao |naoDeveAtualizarAtorNaoEncontrado|
| Ator | atualizacao |deveAtualizaUmAtor|
| Ator | busca |deveRecuperarTodosAtores|
| Ator | busca |deveRecuperarSomenteUmAtor|
| Ator | busca |deveRetornarErroQuandoBuscarAtorInexistente|
| Ator | exclusao |deveExcluirUmAtor|
| Ator | exclusao |naoDeveExcluirAtorNaoEncontrado|

### 4. Configurações:
1. Navegar pelo console do h2
**http://localhost:8080/h2-console** 
![Configurar e navegar pelo console do h2](https://raw.githubusercontent.com/leonardofa/themoviedb-clone/master/readme/resource/img/h2-console.png)

2. Modelo relacional do banco
![MER do TheMovieDB - CLONE](https://raw.githubusercontent.com/leonardofa/themoviedb-clone/master/readme/resource/img/mer.png)

3. O arquivo **start-with-docker.sh** testa, builda e sobe o projeto. Requisitos:
- [ ] Java 11
- [ ] Docker
- [ ] Linux ou converter para bath

4. Cobertura de código nos testes com Jacoco. Relatório pode ser visto em: target/site/jacoco/index.html