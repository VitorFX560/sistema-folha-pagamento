-- =====================================================================
-- 01_schema.sql - Estrutura do Banco de Dados (PostgreSQL)
-- Projeto: Folha Pay (Sistema de Folha de Pagamento)
-- Estratégia JPA: JOINED para hierarquia de Colaborador
-- =====================================================================

CREATE TABLE IF NOT EXISTS colaborador (
    matricula VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    salario_base NUMERIC(12, 2) NOT NULL CHECK (salario_base >= 0)
);

CREATE TABLE IF NOT EXISTS colaborador_padrao (
    matricula VARCHAR(50) PRIMARY KEY REFERENCES colaborador(matricula) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS colaborador_comissionado (
    matricula VARCHAR(50) PRIMARY KEY REFERENCES colaborador(matricula) ON DELETE CASCADE,
    valor_vendas NUMERIC(12, 2) DEFAULT 0 CHECK (valor_vendas >= 0),
    percentual_comissao NUMERIC(5, 2) DEFAULT 0 CHECK (percentual_comissao >= 0)
);

CREATE TABLE IF NOT EXISTS colaborador_producao (
    matricula VARCHAR(50) PRIMARY KEY REFERENCES colaborador(matricula) ON DELETE CASCADE,
    quantidade_produzida INTEGER DEFAULT 0 CHECK (quantidade_produzida >= 0),
    valor_por_unidade NUMERIC(12, 2) DEFAULT 0 CHECK (valor_por_unidade >= 0)
);