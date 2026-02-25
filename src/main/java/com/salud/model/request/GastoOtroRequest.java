package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class GastoOtroRequest {
    private Long id;
    private String usuario;
    private LocalDate desde;
    private LocalDate hasta;
    private Long idPaciente;
    private Long idEspecialidad;
    private Long idCentro;
    private String tipo;
    private double monto;
    private String descripcion;
    private Date fecha;
    private String flgFinanciado;

}
