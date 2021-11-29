# :computer: quadrinho-api
API REST para gerenciar quadrinhos de usuários.

Este projeto é o desafio do programa Aceleração Acadêmica Sênior da ZUP.

### :bookmark_tabs: Tabela de conteúdos
* [Status do projeto](#status)
* [Tecnologias](#tecnologias)
* [Features](#features)
* [Pré-requisitos](#requisitos)
* [Executando a aplicação](#executando)
* [Testando a aplicação](#testando)
* [Licença](#licenca)

<a name="status"/></a>
### :hourglass: Status do projeto
Concluído.

<a name="tecnologias"/></a>
### :hammer_and_wrench: Tecnologias

As seguintes tecnologias e ferramentas foram utilizadas no desenvolvimento deste projeto:

- [Java 11](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Boot Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
- [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [SpringFox Swagger](http://springfox.github.io/springfox/)
- [Flyway](https://flywaydb.org/)
- [Maven](https://maven.apache.org/)
- [H2](https://www.h2database.com)
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

<a name="features"/></a>
### :page_with_curl: Features
- [x] Cadastrar um usuário
- [x] Cadastrar um quadrinho para um determinado usuário
- [x] Consultar a lista de usuários cadastrados na aplicação
- [x] Consultar a lista de quadrinhos de um determinado usuário

<a name="requisitos"/></a>
### :pencil: Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

<a name="executando"/></a>
### :rocket: Executando a aplicação

```bash
# Clone este repositório
$ git clone https://github.com/antonioeloy/quadrinho-api.git

# Na pasta raiz do projeto, execute o container da aplicação
$ docker-compose up

# A aplicação iniciará na porta 8080
```

<a name="testando"/></a>
### :gear: Testando a aplicação

Para testar a API, você pode utilizar a ferramenta [Postman](https://www.postman.com/).

Também é possível testar a aplicação acessando a documentação gerada com a biblioteca [SpringFox Swagger](http://springfox.github.io/springfox/).

```
http://localhost:8080/swagger-ui.html
```

<h1 align="center">
  <img alt="documentacao" title="documentacao" src="documentacao_api.PNG" />
</h1>

<a name="licenca"/></a>
### :copyright: Licença

Este projeto está licenciado nos termos da licença MIT.
