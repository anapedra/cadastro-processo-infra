-- ========================================
-- Tabela principal de usuários
-- ========================================
CREATE TABLE IF NOT EXISTS tb_user (
    id BIGSERIAL PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,

    cpf VARCHAR(14) NOT NULL UNIQUE,

    email VARCHAR(150) NOT NULL UNIQUE,

    senha VARCHAR(255) NOT NULL,

    tel VARCHAR(20),

    data_nascimento DATE
);

-- ========================================
-- Índices extras (performance produção)
-- ========================================
CREATE INDEX idx_user_email ON tb_user(email);
CREATE INDEX idx_user_cpf   ON tb_user(cpf);


-- ========================================
-- Seeds iniciais (ambiente PROD/DEV seguro)
-- Apenas dados de teste
-- ========================================

INSERT INTO tb_user (nome, cpf, email, senha, tel, data_nascimento)
VALUES
('Ana Teste',  '529.982.247-25', 'ana@asantanadev.com',  'Senha@123', '11999999999', '1998-01-10'),

('Maria Silva', '111.444.777-35', 'maria@asantanadev.com', 'Senha@123', '11988888888', '1995-05-20'),

('Joana Costa', '935.411.347-80', 'joana@asantanadev.com', 'Senha@123', '11977777777', '1989-03-15')

ON CONFLICT DO NOTHING;
