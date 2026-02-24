package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "financiamiento")
@Data
@NoArgsConstructor
public class Financiamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer anio;

    private String mes;

    @Column(name = "saldo_inicial")
    private double saldoInicial;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
