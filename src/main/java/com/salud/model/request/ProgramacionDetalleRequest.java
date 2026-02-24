package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProgramacionDetalleRequest {
    private Long id;
    private Long idProgramacion;
    private String tarea;
    private Integer orden;

}
