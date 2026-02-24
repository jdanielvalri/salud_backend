package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class CitaGastoRequest {
    private Long id;
    private Long idCita;
    private String item;
    private Long idCentro;
    private Integer cantidad;
    private Date fecha;
    private double monto;
    private String flgFinanciado;
}
