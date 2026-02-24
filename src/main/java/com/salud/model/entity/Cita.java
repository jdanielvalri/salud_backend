package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cita")
@Data
@NoArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_centro")
    private Long idCentro;

    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    private Date fecha;

    private LocalDateTime hora;

    @Column(name = "monto_total")
    private double montoTotal;

    private String estado;

    private String comentario;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
