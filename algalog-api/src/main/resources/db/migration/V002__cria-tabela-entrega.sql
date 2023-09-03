CREATE TABLE entrega(
    id serial PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    taxa decimal(10,2) NOT NULL,
    status varchar(20) NOT NULL,
    data_pedido timestamp without time zone NOT NULL DEFAULT now(),
    data_finalizacao timestamp without time zone,

    destinatario_nome varchar(60) NOT NULL,
    destinatario_logradouro varchar(255) NOT NULL,
    destinatario_numero varchar(30) NOT NULL,
    destinatario_complemento varchar(60) NOT NULL,
    destinatario_bairro varchar(30) NOT NULL
);

ALTER TABLE entrega ADD CONSTRAINT fk_entrega_cliente
FOREIGN KEY (cliente_id) REFERENCES cliente (id);