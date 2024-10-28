create table revinfo (
    rev bigint not null auto_increment,
    revtstmp datetime(6),
    primary key (rev)
) engine=InnoDB;
create table usuario (
    id BINARY(16) not null,
    cpf varchar(15) not null,
    created_at datetime(6) not null,
    created_by varchar(255),
    data_nascimento datetime(6) not null,
    bairro varchar(128) not null,
    cep varchar(16) not null,
    cidade varchar(128) not null,
    complemento varchar(128) not null,
    estado varchar(128) not null,
    numero varchar(64) not null,
    rua varchar(256) not null,
    nome varchar(256) not null,
    status tinyint,
    updated_at datetime(6),
    updated_by varchar(255),
    version integer not null,
    primary key (id)
) engine=InnoDB;
create table usuario_aud (
    id BINARY(16) not null,
    rev bigint not null,
    revtype tinyint,
    cpf varchar(15),
    created_at datetime(6),
    created_by varchar(255),
    data_nascimento datetime(6),
    bairro varchar(128),
    cep varchar(16),
    cidade varchar(128),
    complemento varchar(128),
    estado varchar(128),
    numero varchar(64),
    rua varchar(256),
    nome varchar(256),
    status tinyint,
    updated_at datetime(6),
    updated_by varchar(255),
    primary key (rev, id)
) engine=InnoDB;
alter table usuario_aud
   add constraint FK74gdm3bhlqa3diq16ouihfq6e
   foreign key (rev)
   references revinfo (rev);
