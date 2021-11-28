create table usuarios (
	id bigint not null auto_increment,
	nome varchar(100) not null,
	email varchar(50) not null unique,
	cpf varchar(11) not null unique,
	data_nascimento date not null,
	primary key(id)
);