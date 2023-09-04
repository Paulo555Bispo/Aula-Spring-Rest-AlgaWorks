package com.algaworks.algalog.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {

    @NotBlank
    @Column(name = "destinatario_nome")
    private String nome;

    @NotBlank
    @Column(name = "destinatario_logradouro")
    private String logradouro;

    @NotBlank
    @Column(name = "destinatario_numero")
    private String numero;

    @NotBlank
    @Column(name = "destinatario_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "destinatario_bairro")
    private String bairro;

}
