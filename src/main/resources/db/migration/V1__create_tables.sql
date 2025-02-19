CREATE TABLE aluno(
    id bigint not null auto_increment,
    nome varchar(150),
    idade int,
    peso float,
    altura float,
    PRIMARY KEY(id)
);

CREATE TABLE usuario(
    id bigint not null auto_increment,
    username varchar(45) not null,
    email varchar(90) not null,
    password varchar(64) not null,
    nome_completo varchar(100) not null,
    role varchar(45) not null,
    enable tinyint(4),
    PRIMARY KEY(id)
);

CREATE TABLE turma(
    id bigint not null auto_increment,
    nome varchar(150),
    local varchar(150),
    PRIMARY KEY(id)
);

ALTER TABLE aluno ADD COLUMN turma bigint;

ALTER TABLE aluno ADD CONSTRAINT fk_turma FOREIGN KEY(turma) REFERENCES turma(id);