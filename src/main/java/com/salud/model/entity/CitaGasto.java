package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cita_gasto")
@Data
@NoArgsConstructor
public class CitaGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "id_centro")
    private Long idCentro;

    private String item;

    private Date fecha;

    private Integer cantidad;

    private double monto;

    @Column(name = "flg_financiado")
    private String flgFinanciado;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
