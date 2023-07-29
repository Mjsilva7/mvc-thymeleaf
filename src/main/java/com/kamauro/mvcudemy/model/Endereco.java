package com.kamauro.mvcudemy.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ENDERECOS")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Informe o logdradouro")
    @Column(nullable = false)
    private String logradouro;

    @NotBlank(message = "Informe o bairro")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "Informe a cidade")
    @Column(nullable = false)
    private String cidade;

    @NotNull(message = "{NotNull.endereco.uf}")
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Size(min = 9, max = 9, message = "{Size.endereco.cep}")
    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 5)
    private Integer numero;

    @Size(max = 255)
    private String complemento;
    
}
