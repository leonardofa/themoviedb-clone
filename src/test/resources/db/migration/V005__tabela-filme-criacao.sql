create table filme (
	id varchar(64) not null default random_uuid(),
    titulo varchar(128) not null,
    descricao varchar(256) not null,
    resumo varchar(512) not null,
    lancamento DATE not null,
    cadastro TIMESTAMP WITH TIME ZONE not null default now(),
    atualizacao TIMESTAMP WITH TIME ZONE,
    primary key (id)
);
