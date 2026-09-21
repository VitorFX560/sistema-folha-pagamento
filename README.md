
# Sistema de Gestão de Folha de Pagamento 💰

Uma API REST desenvolvida em Spring Boot para gerir colaboradores e calcular automaticamente a folha de pagamento utilizando princípios de Programação Orientada a Objetos (Herança e Polimorfismo).

## 🚀 Tecnologias Utilizadas
* **Java 21** 
* **Spring Boot 3.x**
* **Spring Data JPA** (com estratégia Single Table)
* **H2 Database** (Base de dados em memória)
* **Lombok**
* **Maven**

## ⚙️ Como Executar o Projeto

1. Clone o repositório para a sua máquina.
2. Abra o projeto no VS Code (ou na sua IDE de preferência).
3. No VS Code, abra o ficheiro `src/main/java/com/example/demo/DemoApplication.java` e clique no botão **Run** acima do método `main`.
4. O servidor iniciará na porta `8080`. O banco de dados H2 será recriado automaticamente a cada execução.

## 📌 Endpoints da API

A API base está acessível em: `http://localhost:8080/api/folha`

* **POST `/colaboradores`**: Cadastra um novo colaborador. O sistema suporta três tipos (Padrão, Comissionado, Produção), controlados pelo campo `"tipo_colaborador"`.
* **GET `/colaboradores`**: Retorna a lista de todos os colaboradores registados no sistema.
* **GET `/detalhada`**: Gera o relatório detalhado da folha de pagamento, calculando o `salarioFinal` de cada colaborador com base nas suas regras específicas.
* **GET `/resumo`**: Emite um resumo consolidado com o total de colaboradores e a soma total a ser paga pela empresa.

## 📝 Notas para a Equipa de Testes
Ao enviar o payload JSON para a rota de cadastro, é fundamental incluir o atributo discriminador `"tipo_colaborador"` (ex: `"COMISSIONADO"`, `"PADRAO"` ou `"PRODUCAO"`) no corpo da requisição. Isso garante que o sistema saiba exatamente qual regra de cálculo instanciar e aplicar.