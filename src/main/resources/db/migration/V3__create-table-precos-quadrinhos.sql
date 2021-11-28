create table precos_quadrinhos (
	id bigint not null auto_increment primary key,
	tipo varchar(50) not null,
	preco decimal(18,2) not null,
	quadrinho_id bigint not null,
	
	foreign key(quadrinho_id) references quadrinhos(id)
);