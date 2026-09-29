# Folha Clara

Frontend web para gestão de colaboradores e visualização da folha de pagamento, integrado à API Spring Boot existente.

## Requisitos

- Node.js 20 ou superior e npm.
- Backend iniciado em `http://localhost:8080`.

## Instalação e execução

```bash
npm install
cp .env.example .env
npm run dev
```

No PowerShell, copie a configuração com `Copy-Item .env.example .env`. O Vite inicia normalmente em `http://localhost:5173`.

## Configuração da API

Defina `VITE_API_URL` no `.env`. O valor padrão é `http://localhost:8080/api/folha`.

Endpoints usados conforme `FolhaPagamentoController`:

- `GET /colaboradores`: lista os cadastros e seus campos específicos.
- `POST /colaboradores`: cria colaborador; o backend gera a matrícula.
- `GET /detalhada`: retorna matrícula, nome, tipoColaborador, salarioBase e salarioFinal.
- `GET /resumo`: retorna quantidadeColaboradores e valorTotalFolha.

O discriminador enviado no POST é `tipo_colaborador`, com `PADRAO`, `COMISSIONADO` ou `PRODUCAO`. O cadastro envia `nome` e `salarioBase`, além de `valorVendas`/`percentualComissao` ou `quantidadeProduzida`/`valorPorUnidade` quando aplicável. O resultado oficial da folha sempre vem da API.

Se o navegador bloquear as chamadas por CORS, o backend precisa permitir a origem do Vite (`http://localhost:5173`). Não há configuração CORS no backend auditado. O frontend informa esse problema na mensagem de conexão; nenhuma alteração foi feita ao backend.

## Comandos

- `npm run dev`: servidor local de desenvolvimento.
- `npm run build`: verificação TypeScript e build de produção.
- `npm run lint`: análise estática ESLint.
- `npm run preview`: prévia do build.

## Estrutura

- `src/App.tsx`: layout, rotas e páginas.
- `src/api.ts`: cliente HTTP, timeout e erros centralizados.
- `src/types.ts`: contratos TypeScript correspondentes ao backend.
- `src/styles.css`: design system e estilos responsivos.
- `.env.example`: configuração local da API.

## Tecnologias

React, TypeScript, Vite, React Router e Lucide. A interface está em português brasileiro e usa `Intl.NumberFormat` para exibir valores monetários. A API não implementa edição nem exclusão, portanto essas ações não são apresentadas.
