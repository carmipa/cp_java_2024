create sequence clientes_id_cliente_seq order nocache
/

create sequence constatos_id_contato_seq order nocache
/

create sequence c_p_id_cp_seq order nocache
/

create sequence pagamentos_id_pagamento_seq order nocache
/

create sequence s_c_id_sc_seq order nocache
/

create sequence seguros_id_seguro_seq order nocache
/

create sequence seq_clientes
/

create sequence seq_contatos
/

create sequence seq_enderecos
/

create sequence s_p_id_sp_seq order nocache
/

create sequence pagamento_seq nocache
/

create table clientes (
   id_cliente            number,
   tipo_cliente          varchar2(20),
   nome                  varchar2(100),
   tipo_documento        varchar2(20),
   numero_documento      varchar2(20),
   data_nascimento       varchar2(10),
   enderecos_id_endereco number,
   contatos_id_contato   number,
   s_c_id_sc             number,
   c_p_id_cp             number
)
/

create table contatos (
   id_contato number,
   telefone   varchar2(20),
   email      varchar2(100),
   contato    varchar2(100)
)
/

create table c_p (
   id_cp number
)
/

create table enderecos (
   id_endereco number,
   numero      number(5),
   cep         varchar2(10),
   logradouro  varchar2(100),
   bairro      varchar2(100),
   cidade      varchar2(100),
   estado      varchar2(50),
   complemento varchar2(100)
)
/

create table pagamentos (
   id_pagamento    number,
   data_pagamento  varchar2(10),
   tipo_pagamento  varchar2(30),
   forma_pagamento varchar2(50),
   parcelas        number,
   valor_parcela   number,
   desconto        number,
   valor_total     number,
   s_p_id_sp       number,
   c_p_id_cp       number
)
/

create trigger trg_pagamento_id before
   insert on pagamentos
   for each row
   when ( new.id_pagamento is null )
begin
   select pagamento_seq.nextval
     into :new.id_pagamento
     from dual;
end;
/

create table s_c (
   id_sc number
)
/

create table seguros (
   id_seguro number,
   tipo      varchar2(50),
   perfil    varchar2(50),
   valor     number,
   s_c_id_sc number,
   s_p_id_sp number
)
/

create trigger seguros_id_seguro_trg before
   insert on seguros
   for each row
begin
   select seguros_id_seguro_seq.nextval
     into :new.id_seguro
     from dual;
end;
/

create table s_p (
   id_sp number
)
/