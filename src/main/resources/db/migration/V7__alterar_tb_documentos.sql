ALTER TABLE tb_documentos
    ADD COLUMN usuario_id BIGINT;

ALTER TABLE tb_documentos
    ADD CONSTRAINT fk_documento_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES tb_usuarios(id);