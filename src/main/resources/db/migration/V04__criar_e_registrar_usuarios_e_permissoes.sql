create table usuario(
codigo bigint (20) not null primary key,
nome varchar(50) not null,
email varchar(50) not null,
senha varchar (150) not null
) ENGINE=innoDB default charset=utf8;

create table permissao(
codigo bigint (20) not null primary key,
descricao varchar(50) not null
) ENGINE=innoDB default charset=utf8;

create table usuario_permicao (
codigo_usuario bigint (20) not null,
codigo_permissao bigint (20) not null,
primary key (codigo_usuario, codigo_permissao),
foreign key(codigo_usuario) references usuario(codigo),
foreign key(codigo_permissao) references permissao(codigo)
) ENGINE=innoDB default charset=utf8;
