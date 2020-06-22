create table serie_atores (
	serie_id varchar(255) not null,
	atores_id varchar(255) not null
);
alter table serie_atores add constraint fk_serie_ator foreign key (atores_id) references ator;
alter table serie_atores add constraint fk_ator_serie foreign key (serie_id) references serie;       