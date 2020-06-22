create table episodio_atores (
	episodio_id varchar(64) not null,
	atores_id varchar(64) not null
);
alter table episodio_atores add constraint fk_episosio_ator foreign key (atores_id) references ator;
alter table episodio_atores add constraint fk_ator_episodio foreign key (episodio_id) references episodio;