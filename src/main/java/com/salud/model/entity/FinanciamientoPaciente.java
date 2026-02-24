package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "financiamiento_paciente")
@Data
@NoArgsConstructor
public class FinanciamientoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_financiamiento")
    private Long idFinanciamiento;

    @Column(name = "id_paciente")
    private Long idPaciente;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
