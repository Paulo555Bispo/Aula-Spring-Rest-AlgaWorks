CREATE TABLE public.cliente(
	id serial PRIMARY KEY,
	nome VARCHAR ( 60 ) UNIQUE NOT NULL,
	email VARCHAR ( 255 ) NOT NULL,
	telefone VARCHAR ( 20 ) UNIQUE NOT NULL
);