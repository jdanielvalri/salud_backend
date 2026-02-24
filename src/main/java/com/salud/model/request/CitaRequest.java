package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class CitaRequest {
    private String usuario;
    private LocalDate desde;
    private LocalDate hasta;
    private Long idCita;
    private Long idPaciente;
    private Long idEspecialidad;
    private Long idMedico;
    private Long idCentro;
    private String estado;
    private Date fecha;
    private LocalDateTime hora;
    private String comentario;

}
