create sequence CLIENTES_ID_CLIENTE_SEQ
    start with 5
    order
    nocache
/

create sequence CONSTATOS_ID_CONTATO_SEQ
    order
    nocache
/

create sequence C_P_ID_CP_SEQ
    order
    nocache
/

create sequence PAGAMENTOS_ID_PAGAMENTO_SEQ
    order
    nocache
/

create sequence S_C_ID_SC_SEQ
    order
    nocache
/

create sequence SEGUROS_ID_SEGURO_SEQ
    order
    nocache
/

create sequence SEQ_CLIENTES
    start with 21
/

create sequence SEQ_CONTATOS
    start with 21
/

create sequence SEQ_ENDERECOS
    start with 21
/

create sequence S_P_ID_SP_SEQ
    order
    nocache
/

create table CLIENTES
(
    ID_CLIENTE            NUMBER,
    TIPO_CLIENTE          VARCHAR2(20),
    NOME                  VARCHAR2(100),
    TIPO_DOCUMENTO        VARCHAR2(20),
    NUMERO_DOCUMENTO      VARCHAR2(20),
    DATA_NASCIMENTO       VARCHAR2(10),
    ENDERECOS_ID_ENDERECO NUMBER,
    CONTATOS_ID_CONTATO   NUMBER,
    S_C_ID_SC             NUMBER,
    C_P_ID_CP             NUMBER
)
/

create table CONTATOS
(
    ID_CONTATO NUMBER,
    TELEFONE   VARCHAR2(20),
    EMAIL      VARCHAR2(100),
    CONTATO    VARCHAR2(100)
)
/

create table C_P
(
    ID_CP NUMBER
)
/

create table ENDERECOS
(
    ID_ENDERECO NUMBER,
    NUMERO      NUMBER(5),
    CEP         VARCHAR2(10),
    LOGRADOURO  VARCHAR2(100),
    BAIRRO      VARCHAR2(100),
    CIDADE      VARCHAR2(100),
    ESTADO      VARCHAR2(50),
    COMPLEMENTO VARCHAR2(100)
)
/

create table PAGAMENTOS
(
    ID_PAGAMENTO    NUMBER,
    DATA_PAGAMENTO  VARCHAR2(10),
    TIPO_PAGAMENTO  VARCHAR2(30),
    FORMA_PAGAMENTO VARCHAR2(50),
    PARCELAS        VARCHAR2(5),
    VALOR_PARCELA   NUMBER,
    DESCONTO        NUMBER,
    VALOR_TOTAL     NUMBER,
    S_P_ID_SP       NUMBER,
    C_P_ID_CP       NUMBER
)
/

create table S_C
(
    ID_SC NUMBER
)
/

create table SEGUROS
(
    ID_SEGURO NUMBER,
    TIPO      VARCHAR2(50),
    PERFIL    VARCHAR2(50),
    VALOR     NUMBER,
    S_C_ID_SC NUMBER,
    S_P_ID_SP NUMBER
)
/

create or replace trigger SEGUROS_ID_SEGURO_TRG
    before insert
    on SEGUROS
    for each row
BEGIN
    SELECT seguros_id_seguro_seq.NEXTVAL INTO :new.id_seguro FROM dual;
END;
/

create table S_P
(
    ID_SP NUMBER
)
/


