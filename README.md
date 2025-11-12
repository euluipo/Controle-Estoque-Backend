# Sistema de Controle de Estoque 📦
### **Repositório Back-End**

Projeto desenvolvido para a disciplina **Sistemas Distribuídos e Mobile** da **Universidade do Sul de Santa Catarina - UNISUL**.

---

**Link Repositório Front-end React:** https://github.com/euluipo/Controle-Estoque-Frontend-React

**Link Repositório Front-end Java Swing:** https://github.com/euluipo/Controle-Estoque-Frontend

---

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

## 🧭 Requisitos do Sistema

### ✅ Requisitos Funcionais
1. O sistema permite o **cadastro, consulta, atualização e exclusão de produtos e categorias** por meio de **API REST** (`/api/produtos`, `/api/categorias`).  
2. O sistema possibilita o **registro de movimentações de estoque**, diferenciando **entradas e saídas**.  
3. O sistema permite o **reajuste de preços**, tanto de forma **unitária** quanto **em massa**, com base em um percentual definido pelo usuário.  
4. O sistema gera **relatórios consolidados** de produtos, movimentações e balanço geral.  
5. O sistema permite a **consulta e exibição da lista de preços atualizada**.  
6. O sistema oferece **autenticação e controle de usuários**, restringindo o acesso a endpoints protegidos.  
7. O sistema permitie **integração com banco de dados relacional**, garantindo persistência confiável dos dados.  

---

### ⚙️ Requisitos Não Funcionais
1. Desenvolvido em **Java 21** com **Spring Boot**.  
2. Arquitetura organizada segundo o padrão **MVC (Model-View-Controller)**.  
3. **Persistência de dados** implementada com **JPA/Hibernate** e **MySQL**.  
4. **Segurança** implementada nos endpoints e **criptografia de senhas** para proteção de credenciais.  
5. **Código documentado** seguindo o padrão **Javadoc**, com clareza nas classes, métodos e atributos.  
6. **Execução eficiente**, com tempos de resposta adequados nas operações de consulta e gravação.   

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
