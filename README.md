# Folha Pay — Sistema de Folha de Pagamento

Sistema web para gerenciamento de colaboradores, cálculo automático de remunerações (Padrão, Comissionado e Produção) e consolidação de folha de pagamento.

## Tecnologias Utilizadas
- **Backend:** Java 21, Spring Boot, Spring Security (JWT), Spring Data JPA / Hibernate
- **Frontend:** React + Vite
- **Banco de Dados:** PostgreSQL (estratégia JPA `InheritanceType.JOINED`)

---

## Pré-requisitos
Antes de começar, certifique-se de ter instalado na máquina:
- [Java 21 (JDK)](https://adoptium.net/)
- [Node.js (LTS)](https://nodejs.org/)
- [PostgreSQL](https://www.postgresql.org/) (com pgAdmin ou `psql`)
- [Git](https://git-scm.com/)

---

## Como rodar o projeto localmente (Branch `develop`)

### 1. Clonar o repositório
```bash
git clone -b develop [https://github.com/VitorFX560/sistema-folha-pagamento.git](https://github.com/VitorFX560/sistema-folha-pagamento.git)
cd sistema-folha-pagamento
```

### 2. Configurar o Banco de Dados (PostgreSQL)
1. Crie um banco de dados vazio chamado **`folha_pagamento`**.
2. Execute os scripts localizados na pasta `database/` na seguinte ordem:
   - `database/01_schema.sql` (cria as tabelas e restrições)
   - `database/02_seed.sql` (insere a carga inicial de 6 colaboradores)
3. Abra o arquivo `src/main/resources/application.properties` e ajuste o usuário e a senha do PostgreSQL conforme a configuração da sua máquina:
   ```properties
   spring.datasource.username=postgres
   spring.datasource.password=SUA_SENHA_AQUI
   ```

### 3. Executar o Backend (Spring Boot)
Na raiz do projeto, rode:
```bash
./mvnw spring-boot:run
```
*(Ou execute a classe principal diretamente pela extensão Java no VS Code).*

### 4. Executar o Frontend (React + Vite)
Em um **segundo terminal**, acesse a pasta do frontend, instale as dependências e inicie o servidor:
```bash
cd frontend
npm install
npm run dev
```
Acesse a aplicação pelo endereço exibido no terminal (geralmente `http://localhost:5173`).

---

## Fluxo de Versionamento (GitFlow)
Seguindo o Plano de Garantia da Qualidade do projeto, **não faça alterações diretas em `develop` ou `main`**:
- **Novas tarefas (incluindo criação de testes):** crie uma branch `feature/nome-da-feature` a partir da `develop`.
- **Correções de bugs encontrados nos testes:** crie uma branch `fix/nome-do-bug` a partir da `develop`.
- **Commits:** utilize o padrão *Conventional Commits* (`feat:`, `fix:`, `docs:`).
- **Pull Request:** toda alteração deve ser mesclada na `develop` via Pull Request com *Code Review* prévio.
