package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinanciamientoDetalleRequest {
    private Long id;
    private Long idFinanciamiento;
    private String usuario;
    private Long idAportante;
    private Integer anio;
    private String mes;
    private double esperado;
    private double entregado;

}
