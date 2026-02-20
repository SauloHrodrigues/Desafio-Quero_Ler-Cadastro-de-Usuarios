CREATE TABLE tb_usuarios (
                             id BIGSERIAL PRIMARY KEY,

                             nome VARCHAR(80) NOT NULL,

                             email VARCHAR(150) NOT NULL UNIQUE,

                             cpf VARCHAR(14) NOT NULL UNIQUE,

                             data_nascimento DATE,

                             aceite_termos BOOLEAN NOT NULL,

                             cidade VARCHAR(80),

                             estado VARCHAR(100),

                             pais VARCHAR(100),

                             perfil VARCHAR(50) NOT NULL,

                             login VARCHAR(80) NOT NULL UNIQUE,

                             senha VARCHAR(255) NOT NULL
);
INSERT INTO tb_usuarios (
    nome,
    email,
    cpf,
    data_nascimento,
    aceite_termos,
    cidade,
    estado,
    pais,
    perfil,
    login,
    senha
)
SELECT
    'Administrador do Sistema',
    'admin@queroler.com',
    '00000000002',
    '1990-01-01',
    true,
    'Campinas',
    'SP',
    'Brasil',

    'ADMINISTRADOR',
    'admin',
    '$2a$10$610xJj0qHTLX9d9UFRpXQeE70e/GDSGj4Tf2fO1WDSmpliyLog6Xm'
    WHERE NOT EXISTS (
    SELECT 1 FROM tb_usuarios WHERE email = 'admin@queroler.com'
);
