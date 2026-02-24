package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProgramacionRequest {
    private Long id;
    private Long idPaciente;
    private Date fechaDesde;
    private Date fechaHasta;
    private String usuario;

}
