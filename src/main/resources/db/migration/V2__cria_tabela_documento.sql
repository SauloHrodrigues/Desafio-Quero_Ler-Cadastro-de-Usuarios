CREATE TABLE tb_documentos (
                               id BIGSERIAL PRIMARY KEY,
                               titulo VARCHAR(255),
                               tipo VARCHAR(100) NOT NULL,
                               conteudo TEXT
);