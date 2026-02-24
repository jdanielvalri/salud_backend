package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "parametros")
@Data
@NoArgsConstructor
public class Parametro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String descripcion;

    @Column(name = "valor_texto")
    private String valorTexto;

    @Column(name = "valor_numero")
    private double valorNumero;

    @Column(name = "valor_fecha")
    private Date valorFecha;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
