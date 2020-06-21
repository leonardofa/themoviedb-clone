create table ator (
	id varchar(128) not null default random_uuid(),
    nome varchar(60) not null unique,
    nascimento DATE not null,
    cadastro TIMESTAMP not null default now(),
    primary key (id)
);
