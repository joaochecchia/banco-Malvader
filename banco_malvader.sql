create database banco_malvader;
use banco_malvader;

-- Tabela usuario
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE,
    telefone VARCHAR(15),
    tipo_usuario ENUM('FUNCIONARIO', 'CLIENTE') NOT NULL,
    senha VARCHAR(50) NOT NULL
);

CREATE INDEX idx_usuario_id ON usuario(id_usuario);
CREATE INDEX idx_nome ON usuario(nome);

-- Tabela funcionario
CREATE TABLE funcionario (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    codigo_funcionario VARCHAR(20) UNIQUE,
    cargo VARCHAR(50),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE INDEX idx_funcionario_id ON funcionario(id_funcionario);
CREATE INDEX idx_usuario_id ON funcionario(id_usuario);

-- Tabela cliente
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE INDEX idx_cliente_id ON cliente(id_cliente);

-- Tabela endereco
CREATE TABLE endereco (
    id_endereco INT AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    local VARCHAR(100),
    numero_casa INT,
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    estado VARCHAR(2),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE INDEX idx_usuario_id ON endereco(id_usuario);

-- Tabela conta
CREATE TABLE conta (
    id_conta INT AUTO_INCREMENT PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE,
    agencia VARCHAR(10),
    saldo DECIMAL(15, 2) DEFAULT 0.00,
    tipo_conta ENUM('POUPANCA', 'CORRENTE') NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

CREATE INDEX idx_conta_id ON conta(id_conta);
CREATE INDEX idx_numero_conta ON conta(numero_conta);

-- Tabela conta_corrente
CREATE TABLE conta_corrente (
    id_conta_corrente INT AUTO_INCREMENT PRIMARY KEY,
    limite DECIMAL(15, 2) DEFAULT 0.00,
    data_vencimento DATE,
    id_conta INT,
    FOREIGN KEY (id_conta) REFERENCES conta(id_conta) ON DELETE CASCADE
);

CREATE INDEX idx_conta_id ON conta_corrente(id_conta);
CREATE INDEX idx_conta_corrente_id ON conta_corrente(id_conta_corrente);

-- Tabela conta_poupanca
CREATE TABLE conta_poupanca (
    id_conta_poupanca INT AUTO_INCREMENT PRIMARY KEY,
    taxa_rendimento DECIMAL(5, 2),
    id_conta INT,
    FOREIGN KEY (id_conta) REFERENCES conta(id_conta) ON DELETE CASCADE
);

CREATE INDEX idx_conta_id ON conta_poupanca(id_conta);
CREATE INDEX idx_conta_poupanca_id ON conta_poupanca(id_conta_poupanca);

-- Tabela transacao
CREATE TABLE transacao (
    id_transacao INT AUTO_INCREMENT PRIMARY KEY,
    tipo_transacao ENUM('DEPOSITO', 'SAQUE', 'TRANSFERENCIA') NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_conta INT,
    FOREIGN KEY (id_conta) REFERENCES conta(id_conta) ON DELETE CASCADE
);

CREATE INDEX idx_transacao_id ON conta_poupanca(id_conta);
CREATE INDEX idx_conta_id ON transacao(id_conta);

-- Tabela relatorio
CREATE TABLE relatorio (
    id_relatorio INT AUTO_INCREMENT PRIMARY KEY,
    tipo_relatorio VARCHAR(50),
    data_geracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    conteudo TEXT,
    id_funcionario INT,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario) ON delete cascade
);

-- Inserir o usuário na tabela usuario
INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha)
VALUES ('Willian', '12345678901', '1990-05-15', '11999999999', 'FUNCIONARIO', 'senha');

-- Inserir o funcionário na tabela funcionario (supondo que o id_usuario seja 1)
INSERT INTO funcionario (codigo_funcionario, cargo, id_usuario)
VALUES ('FUNC001', 'Gerente', 1);

-- Inserir o endereço para o usuário (funcionário)
INSERT INTO endereco (cep, local, numero_casa, bairro, cidade, estado, id_usuario)
VALUES ('12345-678', 'Rua das Flores', 123, 'Centro', 'Brasilia', 'GO', 1);