create table filme_atores (
	filme_id varchar(64) not null,
	atores_id varchar(64) not null
);
alter table filme_atores add constraint fk_filme_ator foreign key (atores_id) references ator;
alter table filme_atores add constraint fk_ator_filme foreign key (filme_id) references filme;