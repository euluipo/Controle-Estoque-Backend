-- --------------------------------------------------------
--            SCRIPT PARA O BANCO DE DADOS CONTROLE-ESTOQUE
-- --------------------------------------------------------

-- 1. CRIAÇÃO DO BANCO DE DADOS
-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS controle_estoque_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE controle_estoque_db;


-- 2. CRIAÇÃO DAS TABELAS
-- --------------------------------------------------------

-- Tabela de Categorias
CREATE TABLE IF NOT EXISTS categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    tamanho ENUM('Pequeno', 'Médio', 'Grande') NOT NULL,
    embalagem ENUM('Lata', 'Vidro', 'Plástico') NOT NULL
);

-- Tabela de Produtos
CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    unidade VARCHAR(20) NOT NULL,
    quantidade_em_estoque INT NOT NULL DEFAULT 0,
    quantidade_minima INT NOT NULL,
    quantidade_maxima INT NOT NULL,
    categoria_id BIGINT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- Tabela de Movimentações
CREATE TABLE IF NOT EXISTS movimentacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    data_movimentacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantidade_movimentada INT NOT NULL,
    tipo_movimentacao ENUM('ENTRADA', 'SAIDA') NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Tabela de Usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    senha VARCHAR(255) NOT NULL
);

-- 3. INSERÇÃO DE DADOS DE EXEMPLO
-- --------------------------------------------------------

-- Inserindo categorias
INSERT INTO categorias (nome, tamanho, embalagem) VALUES
('Limpeza', 'Médio', 'Plástico'),
('Enlatados', 'Pequeno', 'Lata'),
('Bebidas', 'Grande', 'Vidro'),
('Higiene', 'Pequeno', 'Plástico'),
('Cereais', 'Médio', 'Plástico');

-- Inserindo produtos
INSERT INTO produtos (nome, preco_unitario, unidade, quantidade_em_estoque, quantidade_minima, quantidade_maxima, categoria_id) VALUES
('Detergente Ypê', 2.50, 'Unidade', 50, 20, 150, 1),
('Sabão em Pó Omo', 15.75, 'Caixa 1kg', 30, 10, 80, 1),
('Milho em Conserva', 3.20, 'Lata', 80, 40, 200, 2),
('Atum Ralado', 5.50, 'Lata', 60, 30, 150, 2),
('Refrigerante Coca-Cola', 8.50, 'Garrafa 2L', 100, 50, 300, 3),
('Arroz Prato Fino', 25.00, 'Pacote 5kg', 40, 20, 100, 5),
('Feijão Carioca', 9.50, 'Pacote 1kg', 35, 15, 90, 5),
('Sabonete Dove', 3.00, 'Unidade', 120, 60, 400, 4);

-- Inserindo movimentações de exemplo
INSERT INTO movimentacoes (produto_id, data_movimentacao, quantidade_movimentada, tipo_movimentacao) VALUES
(1, '2025-09-29 10:00:00', 10, 'SAIDA'),
(6, '2025-09-29 11:30:00', 30, 'ENTRADA'),
(3, '2025-09-30 09:15:00', 20, 'SAIDA');