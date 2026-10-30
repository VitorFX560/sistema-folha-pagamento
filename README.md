# Sistema de Gestão de Folha de Pagamento - Grupo 06

## Visão Geral
Este projeto é uma aplicação web para o processamento e gestão de folhas de pagamento de colaboradores. O backend é uma API REST desenvolvida em Java com Spring Boot, e o frontend foi desenvolvido como uma Single Page Application (SPA) utilizando React.

> **Nota Importante:** Toda a modelagem do projeto, incluindo Especificação de Requisitos, Documento de Arquitetura, EAP, Cronograma, Responsáveis e Plano de Qualidade/Testes, encontra-se detalhada no documento oficial da entrega acadêmica (Entregável A1).

##  Como Executar o Projeto

1. **Clone o repositório para a sua máquina:**
   `git clone https://github.com/VitorFX560/sistema-folha-pagamento.git`

2. **Aceda à pasta principal do projeto:**
   `cd sistema-folha-pagamento`

3. **Inicie o servidor backend (Spring Boot):**
   `mvnw clean spring-boot:run`

4. **Acesso à Aplicação (Frontend):**
   Com o backend a correr, aceda à interface web através do seu navegador no link: `http://localhost:5173/`

##  Acesso à API e Base de Dados
Durante o desenvolvimento, o servidor Tomcat embutido corre na porta `8080` e utiliza o banco de dados em memória H2.

* **Testes de API:** Aponte as suas requisições REST (via Postman/Thunder Client) para `http://localhost:8080/` (ex: `POST http://localhost:8080/colaboradores`).
* **Consola do Banco de Dados:** Valide os dados persistidos em memória acedendo a `http://localhost:8080/h2-console`.
