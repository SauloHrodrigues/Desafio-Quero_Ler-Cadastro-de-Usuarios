CREATE TABLE tb_usuario_documento (
                                      id BIGSERIAL PRIMARY KEY,
                                      usuario_id BIGINT NOT NULL,
                                      documento_id BIGINT NOT NULL,
                                      data_visualizacao TIMESTAMP,

                                      CONSTRAINT fk_usuario
                                          FOREIGN KEY (usuario_id)
                                              REFERENCES tb_usuarios(id)
                                              ON DELETE CASCADE,

                                      CONSTRAINT fk_documento
                                          FOREIGN KEY (documento_id)
                                              REFERENCES tb_documentos(id)
                                              ON DELETE CASCADE,

                                      CONSTRAINT uk_usuario_documento UNIQUE (usuario_id, documento_id)
);