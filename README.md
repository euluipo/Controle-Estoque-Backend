# Sistema de Controle de Estoque 📦
### **Repositório Back-End**

Projeto desenvolvido para a disciplina **Sistemas Distribuídos e Mobile** da **Universidade do Sul de Santa Catarina - UNISUL**.

## 🎓 Informações Acadêmicas

- **Disciplina:** Sistemas Distribuídos e Mobile
- **Professores:** Osmar de Oliveira Braz Júnior
- **Avaliação:** A3 – Desempenho de compreensão
- **Meta:** Projetar e desenvolver sistemas com arquiteturas baseadas em serviços.

## 👥 Integrantes do Grupo

- Arthur Zamprogna Ventura - 10725111773 - [@arthurventuraza](https://github.com/arthurventuraza/)
- Gabriel Luipo - 1072519471 - [@euluipo](https://github.com/euluipo/)
- Nícolas Gaia Negrão - 1072517389 - [@NickPotato](https://github.com/nickpotato/)
- Pedro Henrique Francio Della Giustina - 10725110773 - [@PedroDella](https://github.com/pedrodella/)

## 🛠️ Tecnologias e Ferramentas Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** MySQL
- **Build Tool:** Apache Maven
- **Servidor:** Apache Tomcat (Embutido no Spring Boot)
- **Documentação da API:** Swagger / OpenAPI 3 (via Springdoc)
- **Controle de Versão:** Git + GitHub

## ⚙️ Funcionalidades do Sistema

- **CRUD de Categorias** via API REST (`/api/categorias`)
- **CRUD de Produtos** via API REST (`/api/produtos`)
- **Movimentação de Estoque (Entrada/Saída)**
- **Reajuste de preços em massa e unitariamente por percentual**
- **Endpoints para geração de relatórios**

- ## 📖 Documentação da API (Swagger)

A API possui uma documentação interativa gerada automaticamente com o Swagger. Através dela, é possível ver todos os endpoints disponíveis, seus parâmetros, os modelos de dados e **testar a API diretamente pelo navegador**.

Com a aplicação rodando, acesse:
- **Swagger UI (Interface Gráfica):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Definição OpenAPI (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 🧱 Estrutura do Projeto

O projeto segue a arquitetura em camadas padrão do Spring Boot:

- `src/main/java/br/com/oaksystem/oaksystem`: Pacote raiz da aplicação.
  - `/model`: Classes de entidade que mapeiam as tabelas do banco (@Entity).
  - `/repository`: Interfaces do Spring Data JPA para acesso aos dados.
  - `/service`: Camada que contém as regras de negócio da aplicação.
  - `/controller`: Camada que expõe os endpoints da API REST (@RestController).
- `src/main/resources/application.properties`: Arquivo de configuração principal (conexão com banco, porta do servidor, etc.).
- `pom.xml`: Arquivo de configuração do Maven, onde são gerenciadas as dependências do projeto.
