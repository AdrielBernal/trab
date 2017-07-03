create table produto(
    id int not null,
    descricao varchar not null,
    valor_dolar varchar not null,
    primary key(id)
)
create table cliente(
    id serial,
    nome varchar not null,
    cpf varchar not null,
    telefone varchar not null,
    primary key(id)
)
create table orcamento(
id serial,
descricao varchar not null,
id_cliente int not null,
valor_dolar numeric not null,
primary key(id),
foreign key (id_cliente) references cliente(id)
)