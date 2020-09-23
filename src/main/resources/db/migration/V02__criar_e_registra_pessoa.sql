
CREATE TABLE pessoa (
codigo bigint(20) primary key auto_increment,
ativo INT (1) not null,
logradouro varchar(30) not null,
numero bigint(10) not null,
bairro varchar(30) not null,
cep varchar (10) not null,
cidade varchar (10) not null,
estado varchar (10) not null
)engine=InnoDB default charset=utf8;

INSERT INTO pessoa (ativo,logradouro,numero,bairro,cep,cidade,estado) VALUES (1,'lazaro', 10,'tororo','40050-100','salvador','bahia');
INSERT INTO pessoa (ativo,logradouro,numero,bairro,cep,cidade,estado) VALUES (3,'iapi', 12,'auto das pnbas','40051-100','salvador','bahia');
INSERT INTO pessoa (ativo,logradouro,numero,bairro,cep,cidade,estado) VALUES (2,'favrisisco', 11,'iapi','40040-100','salvador','bahia');
INSERT INTO pessoa (ativo,logradouro,numero,bairro,cep,cidade,estado) VALUES (4,'lauro de freitas', 13,'dique ','40052-100','salvador','bahia');
INSERT INTO pessoa (ativo,logradouro,numero,bairro,cep,cidade,estado) VALUES (5,'calaba', 14,'doron','40054-100','salvador','bahia');
INSERT INTO pessoa (ativo,logradouro,numero,bairro,cep,cidade,estado) VALUES (6,'tranquedo neves', 15,'vasco','40042-100','salvador','baShia');
