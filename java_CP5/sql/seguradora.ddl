-- Gerado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   em:        2024-10-14 16:43:05 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE c_p (
    id_cp NUMBER NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE c_p
    ADD CONSTRAINT c_p_pk PRIMARY KEY ( id_cp ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE clientes (
    id_cliente            NUMBER NOT NULL,
    tipo_cliente          VARCHAR2(20) NOT NULL,
    nome                  VARCHAR2(100) NOT NULL,
    tipo_documento        VARCHAR2(20) NOT NULL,
    numero_documento      VARCHAR2(20) NOT NULL,
    data_nascimento       VARCHAR2(10) NOT NULL,
    enderecos_id_endereco NUMBER NOT NULL,
    constatos_id_contato  NUMBER NOT NULL,
    s_c_id_sc             NUMBER NOT NULL,
    c_p_id_cp             NUMBER NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE clientes
    ADD CONSTRAINT clientes_pk PRIMARY KEY ( id_cliente,
                                             s_c_id_sc,
                                             c_p_id_cp ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE constatos (
    id_contato NUMBER NOT NULL,
    telefone   VARCHAR2(20) NOT NULL,
    email      VARCHAR2(100) NOT NULL,
    contato    VARCHAR2(100) NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE constatos
    ADD CONSTRAINT constatos_pk PRIMARY KEY ( id_contato ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE enderecos (
    id_endereco NUMBER NOT NULL,
    numero      NUMBER(5) NOT NULL,
    cep         VARCHAR2(10) NOT NULL,
    logradouro  VARCHAR2(100) NOT NULL,
    bairro      VARCHAR2(100) NOT NULL,
    cidade      VARCHAR2(100) NOT NULL,
    estado      VARCHAR2(50) NOT NULL,
    complemento VARCHAR2(100) NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE enderecos
    ADD CONSTRAINT enderecos_pk PRIMARY KEY ( id_endereco ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE pagamentos (
    id_pagamento    NUMBER NOT NULL,
    data_pagamento  VARCHAR2(10) NOT NULL,
    tipo_pagamento  VARCHAR2(30) NOT NULL,
    forma_pagemento VARCHAR2(50) NOT NULL,
    parcelas        VARCHAR2(5) NOT NULL,
    valor_parcela   NUMBER NOT NULL,
    desconto        NUMBER NOT NULL,
    valor_total     NUMBER NOT NULL,
    s_p_id_sp       NUMBER NOT NULL,
    c_p_id_cp       NUMBER NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE pagamentos
    ADD CONSTRAINT pagamentos_pk PRIMARY KEY ( id_pagamento,
                                               s_p_id_sp ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE s_c (
    id_sc NUMBER NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE s_c
    ADD CONSTRAINT s_c_pk PRIMARY KEY ( id_sc ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE s_p (
    id_sp NUMBER NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE s_p
    ADD CONSTRAINT s_p_pk PRIMARY KEY ( id_sp ) NOT DEFERRABLE ENABLE VALIDATE;

CREATE TABLE seguros (
    id_seguro NUMBER NOT NULL,
    tipo      VARCHAR2(50) NOT NULL,
    perfil    VARCHAR2(50) NOT NULL,
    valor     NUMBER NOT NULL,
    s_c_id_sc NUMBER NOT NULL,
    s_p_id_sp NUMBER NOT NULL
)
ORGANIZATION HEAP NOCOMPRESS
    NOCACHE
        NOPARALLEL
    NOROWDEPENDENCIES DISABLE ROW MOVEMENT;

ALTER TABLE seguros
    ADD CONSTRAINT seguros_pk PRIMARY KEY ( id_seguro,
                                            s_c_id_sc,
                                            s_p_id_sp ) NOT DEFERRABLE ENABLE VALIDATE;

ALTER TABLE clientes
    ADD CONSTRAINT clientes_c_p_fk FOREIGN KEY ( c_p_id_cp )
        REFERENCES c_p ( id_cp );

ALTER TABLE clientes
    ADD CONSTRAINT clientes_constatos_fk FOREIGN KEY ( constatos_id_contato )
        REFERENCES constatos ( id_contato );

ALTER TABLE clientes
    ADD CONSTRAINT clientes_enderecos_fk FOREIGN KEY ( enderecos_id_endereco )
        REFERENCES enderecos ( id_endereco );

ALTER TABLE clientes
    ADD CONSTRAINT clientes_s_c_fk FOREIGN KEY ( s_c_id_sc )
        REFERENCES s_c ( id_sc );

ALTER TABLE pagamentos
    ADD CONSTRAINT pagamentos_c_p_fk FOREIGN KEY ( c_p_id_cp )
        REFERENCES c_p ( id_cp );

ALTER TABLE pagamentos
    ADD CONSTRAINT pagamentos_s_p_fk FOREIGN KEY ( s_p_id_sp )
        REFERENCES s_p ( id_sp );

ALTER TABLE seguros
    ADD CONSTRAINT seguros_s_c_fk FOREIGN KEY ( s_c_id_sc )
        REFERENCES s_c ( id_sc );

ALTER TABLE seguros
    ADD CONSTRAINT seguros_s_p_fk FOREIGN KEY ( s_p_id_sp )
        REFERENCES s_p ( id_sp );

CREATE SEQUENCE c_p_id_cp_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER c_p_id_cp_trg BEFORE
    INSERT ON c_p
    FOR EACH ROW
    WHEN ( new.id_cp IS NULL )
BEGIN
    :new.id_cp := c_p_id_cp_seq.nextval;
END;
/

CREATE SEQUENCE clientes_id_cliente_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER clientes_id_cliente_trg BEFORE
    INSERT ON clientes
    FOR EACH ROW
    WHEN ( new.id_cliente IS NULL )
BEGIN
    :new.id_cliente := clientes_id_cliente_seq.nextval;
END;
/

CREATE SEQUENCE constatos_id_contato_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER constatos_id_contato_trg BEFORE
    INSERT ON constatos
    FOR EACH ROW
    WHEN ( new.id_contato IS NULL )
BEGIN
    :new.id_contato := constatos_id_contato_seq.nextval;
END;
/

CREATE SEQUENCE pagamentos_id_pagamento_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER pagamentos_id_pagamento_trg BEFORE
    INSERT ON pagamentos
    FOR EACH ROW
    WHEN ( new.id_pagamento IS NULL )
BEGIN
    :new.id_pagamento := pagamentos_id_pagamento_seq.nextval;
END;
/

CREATE SEQUENCE s_c_id_sc_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER s_c_id_sc_trg BEFORE
    INSERT ON s_c
    FOR EACH ROW
    WHEN ( new.id_sc IS NULL )
BEGIN
    :new.id_sc := s_c_id_sc_seq.nextval;
END;
/

CREATE SEQUENCE s_p_id_sp_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER s_p_id_sp_trg BEFORE
    INSERT ON s_p
    FOR EACH ROW
    WHEN ( new.id_sp IS NULL )
BEGIN
    :new.id_sp := s_p_id_sp_seq.nextval;
END;
/

CREATE SEQUENCE seguros_id_seguro_seq START WITH 1 INCREMENT BY 1 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE ORDER;

CREATE OR REPLACE TRIGGER seguros_id_seguro_trg BEFORE
    INSERT ON seguros
    FOR EACH ROW
    WHEN ( new.id_seguro IS NULL )
BEGIN
    :new.id_seguro := seguros_id_seguro_seq.nextval;
END;
/



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             16
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           7
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          7
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
