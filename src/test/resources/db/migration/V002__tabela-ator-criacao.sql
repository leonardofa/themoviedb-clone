create table ator (
	id varchar(120) not null default random_uuid(),
    nome varchar(60) not null unique,
    nascimento DATE not null,
    cadastro TIMESTAMP WITH TIME ZONE not null default now(),
    atualizacao TIMESTAMP WITH TIME ZONE,
    primary key (id)
);
