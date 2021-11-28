create table autores (
	id bigint not null auto_increment primary key,
	uri varchar(100) not null,
	nome varchar(100) not null
);

create table autores_quadrinhos (
	quadrinho_id bigint not null,
	autor_id bigint not null,
	
	primary key(quadrinho_id, autor_id),
	
	foreign key(quadrinho_id) references quadrinhos(id),
	foreign key(autor_id) references autores(id)
);