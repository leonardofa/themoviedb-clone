create table usuario (
	id varchar(128) not null default random_uuid(),
    nome varchar(60) not null,
    email varchar(120) not null unique,
    
    primary key (id)
);
