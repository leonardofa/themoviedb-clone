create table usuario (
	id varchar(120) not null default random_uuid(),
    nome varchar(60) not null,
    email varchar(120) not null unique,
    cadastro TIMESTAMP WITH TIME ZONE not null default now(),
    atualizacao TIMESTAMP WITH TIME ZONE,
    primary key (id)
);
