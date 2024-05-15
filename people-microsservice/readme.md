# Sistema de Gerenciamento de Biblioteca

Este é um sistema de gerenciamento de biblioteca, desenvolvido em Java com o framework Spring Boot e seguindo a arquitetura hexagonal. Utiliza o banco de dados H2 em memória.

## Requisitos

- Java 8 ou superior
- Maven

## Configuração

1. Clone este repositório:

```bash
git clone https://github.com/retxeeD/hexagonal-architecture
```

2. Abrir como projeto o diretório people-microsservice

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

Rotas

    /v1/person/register - Cadastra uma nova pessoa
    /v1/person/rent-book - Alugar um livro
    /v1/person/return-book - Devolver o livro alugado
    /v1/person/consult/{document} - Consultar uma pessoa por documento
    /v1/person/delete/{id} - Excluir uma pessoa por ID

Register

    *document nao pode ser duplicado
    {
        "document" : "12345678901",
        "name": "Carlos Eduardo",
        "rentBook": 1
    }

Rent-book
    
    Se comunica com aplicação de Book para validar existencia do livro que será alugado.
    
    *document deve existir
    *rentBook deve existir
    {
        "document" : "12345678901",
        "rentBook": 1
    }

Return-book

    Valida se o livro q ser devolvido é o livro que realmente foi pego pelo usuário.
    
    *document deve existir
    *rentBook deve ser o mesmo que foi alugado
    {
        "document" : "12345678901",
        "rentBook": 1
    }

Consult document

    Valida se valor informado é um documento válido.

Delete ID
    
    Valida se o valor informado é um UUID válido.