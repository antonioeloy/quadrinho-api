create table quadrinhos (
	id bigint not null auto_increment primary key,
	comic_id integer not null,
	titulo varchar(200) not null,
	isbn varchar(20) not null unique,
	descricao varchar(2000)
);

create table quadrinhos_usuarios (
	usuario_id bigint not null,
	quadrinho_id bigint not null,
	
	primary key(usuario_id, quadrinho_id),
	
	foreign key(usuario_id) references usuarios(id),
	foreign key(quadrinho_id) references quadrinhos(id)
);