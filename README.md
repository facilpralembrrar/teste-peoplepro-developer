# Desafio People Pro - Desenvolvedor Backend
Proposta de solução do teste prático para desenvolvedor

## Descrição

O desafio pede a implementação de uma aplicação com interface API Rest para o cadastro de usuários. A API deverá permitir:

1. Cadastrar usuários;
2. Alterar os dados de um usuário;
3. Obter uma lista de todos os usuários cadastrados;
4. Obter um usuário pelo seu identificador;
5. Inativar um usuário (remoção lógica).

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.5
- JPA
- MySQL 8.0
- Maven
- KeyCloak
- Flyway
- Docker/Docker compose

## Configuração do Projeto para Desenvolvimento

O desenvolvedor deverá ter instalado em sua máquina o JDK 21, além do Docker e Docker Compose. Se a IDE usada para desenvolvimento não der suporte para o Maven, então, o mesmo deverá ser instalado.

## Execução do Projeto

Antes de executar o projeto, faz-se necessário verificar se o Docker e Docker Compose estão instalados na máquina.

## Como Executar

Para executar o projeto localmente, deve-se usar o comando `docker-compose up --build` no diretório raíz do projeto.

Após a execução, a documentação da API, exposta através do Swagger UI, pode ser visualizada acessando a seguinte URL:

[Usuários - API](http://localhost:8989/swagger-ui/index.html)

Para proteção dos endpoints da aplicação através de tokens JWT, foi escolhido o Keycloak como mecanismo de autenticação e autorização. O Keycloak, por padrão, já possui um usuário admin (com senha admin) que pode ser configurado para os testes de acesso da API.
Assim, faz-se necessária inclusão da propriedade de usuário `"username"` nos claims do token a ser gerado pelo Keycloak. Esse processo pode ser visto, por exemplo, nos dois links que seguem: 

https://medium.com/@lakshminp/adding-user-attributes-to-jwt-token-in-keycloak-f3981b7df310

https://www.baeldung.com/keycloak-custom-user-attributes

*A propriedade "username" será usada para preenchimento dos campos "createdBy" e "updatedBy" no cadastro de usuários*

## Estrutura do Projeto

O projeto foi organizado seguindo o padrão **Domain Driven Design (DDD)** com uma **arquitetura hexagonal**. O contexto **Core** está estruturado da seguinte forma:

- **domain**: Grupo que contêm as classes de domínio diretamente relacionadas às regras de negócio da aplicação.

- **application**: Grupo que contêm uma categoria de adaptadores que encaminham adequadamente chamadas externas para métodos presentes nas portas de entrada.

- **infrastructure**: Grupo que recebe as chamadas geradas a partir das classes de domínio, que possuem acesso às portas de saída, e as direciona para um sistema externo, por exemplo, uma base de dados relacional.

## Versionamento da Base de Dados

A criação e o gerenciamento da base de dados MySQL é realizado por meio da ferramenta **Flyway**. Uma única migration foi criada; ela é usada para criar as tabelas da aplicação e gerar as demais estruturas de dados, inclusive as tabelas de auditoria.
