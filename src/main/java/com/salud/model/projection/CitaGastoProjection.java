package com.salud.model.projection;

import java.time.LocalDateTime;
import java.util.Date;

public interface CitaGastoProjection {

    Long getId();
    Long getIdCita();
    Long getIdCentro();
    String getItem();
    Date getFecha();
    String getFechaCadena();
    Integer getCantidad();
    double getMonto();
    String getNombreCentro();
    String getFlgFinanciado();
}
