CREATE TABLE categoria (
codigo bigint(20) primary key auto_increment,
nome varchar(50) not null
)engine=InnoDB default charset=utf8;

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermecado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('Outras');