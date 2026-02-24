CREATE TABLE tb_usuarios (
                             id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(80),
                             email VARCHAR(150) UNIQUE,
                             cpf VARCHAR(14) UNIQUE,
                             data_nascimento DATE,
                             aceite_termos BOOLEAN,
                             cidade VARCHAR(80),
                             estado VARCHAR(100),
                             pais VARCHAR(100),
                             perfil VARCHAR(50) NOT NULL,
                             login VARCHAR(80) NOT NULL UNIQUE,
                             senha VARCHAR(255) NOT NULL
);

CREATE TABLE livros (
                        id BIGSERIAL PRIMARY KEY,

                        titulo VARCHAR(150) NOT NULL,
                        isbn VARCHAR(20) NOT NULL UNIQUE,
                        autor VARCHAR(120) NOT NULL,
                        data_de_publicacao DATE NOT NULL,
                        usuario_id BIGINT NOT NULL,
                        nome_usuario_cadastro VARCHAR(150) NOT NULL,

                        CONSTRAINT fk_livro_usuario
                            FOREIGN KEY (usuario_id)
                                REFERENCES tb_usuarios(id)
);

INSERT INTO tb_usuarios (nome, email, cpf, data_nascimento, aceite_termos, cidade, estado, pais,
                         perfil, login, senha)


SELECT
    'Administrador do Sistema',
    null,
    null,
    null,
    true,
    null,
    null,
    null,
    'ADMINISTRADOR',
    'admin',
    '$2a$10$JH3Tn.std3ux380zmuulYOpeEkq7oTu21W8IrRULajBs7spD5b4YK'
    WHERE NOT EXISTS (
    SELECT 1 FROM tb_usuarios WHERE email = 'admin@queroler.com'
);
