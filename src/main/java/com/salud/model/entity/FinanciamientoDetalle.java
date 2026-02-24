package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "financiamiento_detalle")
@Data
@NoArgsConstructor
public class FinanciamientoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_financiamiento")
    private Long idFinanciamiento;

    @Column(name = "id_aportante")
    private Long idAportante;

    private double esperado;

    private double entregado;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
