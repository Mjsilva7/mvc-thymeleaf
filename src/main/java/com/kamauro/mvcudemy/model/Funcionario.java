package com.kamauro.mvcudemy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal salario;

    @Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @Column(name = "data_saida", columnDefinition = "DATE")
    private LocalDate dataSaida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco_fk")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_cargo_fk")
    private Cargo cargo;
}
