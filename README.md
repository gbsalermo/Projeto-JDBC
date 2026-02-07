# Projeto JDBC com MySQL – Banco de Dados

Este projeto representa meu **primeiro contato prático com banco de dados em Java**, utilizando **JDBC puro**, **MySQL** e a implementação manual do **padrão DAO (Data Access Object)**.

O foco aqui não foi produtividade ou abstração, mas sim **entender na prática como uma aplicação Java se conecta, consulta e manipula dados em um banco relacional**, escrevendo SQL manualmente e controlando todo o fluxo de acesso aos dados.

---

## 🧠 Objetivo do Projeto

Este projeto foi desenvolvido com os seguintes objetivos:

* Compreender o funcionamento do **JDBC (Java Database Connectivity)**
* Realizar operações CRUD diretamente com **SQL**
* Trabalhar com **PreparedStatement** e **ResultSet**
* Implementar o **padrão DAO manualmente**, sem frameworks
* Separar lógica de aplicação e acesso a dados

Trata-se de um projeto **introdutório**, porém foi essencial para meu desenvolvimento como backend Java.

---

## 🧩 Domínio da Aplicação

O sistema trabalha com um domínio simples, ideal para aprendizado inicial:

* **Department** – Representa um departamento da empresa
* **Seller** – Representa um vendedor vinculado a um departamento

### Relacionamento

* Um **Department** pode ter vários **Sellers**
* Cada **Seller** pertence a um único **Department**


---

## 🏛️ Estrutura do Projeto

A organização do código segue a seguinte estrutura:

* **application**
  Contém as classes `Program` e `Program2`, responsáveis por executar e testar as funcionalidades

* **model.entities**
  Classes de domínio (`Seller` e `Department`)

* **model.dao**
  Interfaces que definem os contratos de acesso a dados

* **model.dao.impl**
  Implementações concretas dos DAOs utilizando JDBC

* **db**
  Classes utilitárias para conexão com o banco, fechamento de recursos e tratamento de exceções

Essa separação ajuda a manter o código organizado, desacoplado e fácil de entender.

---

## 🔌 Acesso a Dados com JDBC

Todo o acesso ao banco é feito diretamente via **JDBC**, utilizando:

* `Connection`
* `PreparedStatement`
* `ResultSet`

As consultas SQL são escritas manualmente, permitindo visualizar claramente:

* Como os dados são buscados no banco
* Como os resultados são mapeados para objetos Java
* Como exceções e recursos são tratados

---

## 🧱 Padrão DAO

O padrão **DAO (Data Access Object)** foi implementado manualmente:

* Cada entidade possui um DAO específico
* O código principal conhece apenas **interfaces**, não implementações
* A criação dos DAOs é centralizada na classe **`DaoFactory`**

Esse padrão reduz acoplamento e organiza o acesso a dados de forma mais profissional.

---

## ⚙️ Classes de Execução

### `Program`

Classe responsável por testar as operações relacionadas à entidade **Seller**:

* Buscar vendedor por ID
* Buscar vendedores por departamento
* Listar todos os vendedores
* Inserir, atualizar e remover vendedores

### `Program2`

Classe responsável por testar as operações relacionadas à entidade **Department**:

* Buscar departamento por ID
* Listar todos os departamentos
* Inserir, atualizar e remover departamentos

Essas classes funcionam como **testes manuais**, simulando o uso real do sistema.

---

## 🧪 Banco de Dados

* **MySQL**
* Base de dados: `coursejdbc`
* Script SQL fornecido no material de estudo

A configuração da conexão é feita através do arquivo `db.properties`, mantendo as credenciais fora do código-fonte.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

* Java JDK instalado
* MySQL Server em execução
* MySQL Connector/J configurado no projeto
* Conhecimento básico de SQL (SELECT, INSERT, UPDATE, DELETE)

### Passos

1. Crie a base de dados `coursejdbc` no MySQL
2. Execute o script SQL para criação das tabelas
3. Configure o arquivo `db.properties`
4. Execute as classes `Program` ou `Program2`

---

## 📌 Aprendizados Obtidos

* Funcionamento prático do JDBC
* Escrita e execução de SQL em aplicações Java
* Mapeamento manual de dados para objetos
* Gerenciamento de conexões e recursos
* Importância da organização com DAO

Este projeto serviu como **base fundamental** para entender acesso a dados em Java antes do uso de frameworks ou abstrações mais avançadas.

---

👨‍💻 Projeto desenvolvido por **Gabriel Salermo**, com foco em aprendizado e fundamentos de banco de dados em Java.
