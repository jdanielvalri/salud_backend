package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "gastos_otros")
@Data
@NoArgsConstructor
public class GastoOtro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_centro")
    private Long idCentro;

    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    private Date fecha;

    private double monto;

    private String descripcion;

    @Column(name = "flg_financiado")
    private String flgFinanciado;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
