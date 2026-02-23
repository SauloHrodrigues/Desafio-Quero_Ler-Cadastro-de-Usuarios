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