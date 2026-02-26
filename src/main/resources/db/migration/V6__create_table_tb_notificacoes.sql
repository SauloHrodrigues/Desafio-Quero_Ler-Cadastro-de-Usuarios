CREATE TABLE tb_notificacoes (
                                 id BIGSERIAL PRIMARY KEY,
                                 data_inicio TIMESTAMP,
                                 data_final TIMESTAMP,
                                 lida_em TIMESTAMP,
                                 usuario_id BIGINT NOT NULL,
                                 documento_id BIGINT NOT NULL,
                                 CONSTRAINT fk_notificacao_usuario
                                     FOREIGN KEY (usuario_id)
                                         REFERENCES tb_usuarios (id)
                                         ON DELETE CASCADE,
                                 CONSTRAINT fk_notificacao_documento
                                     FOREIGN KEY (documento_id)
                                         REFERENCES tb_documentos (id)
                                         ON DELETE CASCADE
);