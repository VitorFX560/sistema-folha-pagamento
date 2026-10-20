Sistema de Gestão de Folha de Pagamento

 Visão Geral

Este projeto é uma aplicação para o processamento e gestão de folhas de pagamento de colaboradores. O backend é uma API REST desenvolvida em Java com Spring Boot, aplicando conceitos rigorosos de Programação Orientada a Objetos e Arquitetura de Software (Padrão MVC).

Nota: A documentação completa de arquitetura, diagrama de classes UML e modelagem estrutural encontram-se detalhadas no documento oficial do projeto.

Decisões Técnicas e Abstração de Dados

Encapsulamento e Lombok: O uso da biblioteca Lombok foi estrategicamente removido ou limitado em classes de domínio centrais. Esta decisão visou garantir que os construtores e o encapsulamento das regras de negócio fiquem explícitos, mantendo o código 100% fiel à modelagem UML rigorosa exigida no projeto.

Infraestrutura de Dados: A camada de persistência foi abstraída utilizando Spring Data JPA. Atualmente, o ambiente de desenvolvimento utiliza o banco de dados em memória H2 Database pela agilidade, mas a estrutura já está preparada para uma migração transparente para PostgreSQL no futuro.

 Gestão do Projeto e EAP

O planeamento, alocação de tarefas e o cronograma (Estrutura Analítica do Projeto - EAP) deste projeto foram geridos de forma ágil com recurso ao GitHub Projects. A hierarquia de trabalho estabelecida no quadro Kanban está dividida em:

Milestones: Fases do ciclo de vida do projeto (ex: Modelagem, Desenvolvimento, Testes).

Issues: Pacotes de entrega, definição de responsabilidades e desenvolvimento de funcionalidades principais.

Sub-issues / Tasks: Tarefas granulares e checklists operacionais atribuídas a cada membro do grupo.

 Como Executar o Projeto

Clone o repositório para a sua máquina:

git clone https://github.com/seu-usuario/seu-repositorio.git


Aceda à pasta principal do projeto:

cd seu-repositorio


Limpe as compilações antigas e inicie o servidor backend usando o Maven:

mvnw clean spring-boot:run


Acesso à Aplicação (Frontend):
Aceda à interface da aplicação através do seu navegador no seguinte link:
 http://localhost:5173/

 Testes de API e Base de Dados (Backend)

O servidor Tomcat embutido no Spring Boot estará a correr na porta 8080.

Testes de Rotas (Thunder Client / Postman):
A API REST recebe requisições diretamente na porta do servidor. Para testar o CRUD de colaboradores e o processamento da folha, aponte as requisições para a base da URL:
 http://localhost:8080/ (ex: POST http://localhost:8080/colaboradores)

Acesso à Base de Dados H2:
Para validar os dados persistidos em memória, aceda à consola do H2 no navegador através do link:
 http://localhost:8080/h2-console

 Próximos Passos (Backlog / Fase 2)

Segurança e Autenticação (RF-10): Implementação de funcionalidades de login e restrição de acesso aos endpoints da folha de pagamento (ex: tokens JWT). Mapeado estruturalmente como um Requisito Funcional para a próxima iteração. c]]]



]
