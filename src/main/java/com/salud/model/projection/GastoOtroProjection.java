package com.salud.model.projection;

import java.time.LocalDateTime;
import java.util.Date;

public interface GastoOtroProjection {

    Long getId();
    Long getIdPaciente();
    Long getIdEspecialidad();
    Long getIdCentro();
    String getNombrePaciente();
    String getNombreEspecialidad();
    String getNombreCentro();
    Date getFecha();
    String getFechaCadena();
    double getMontoTotal();
    double getMontoFinanciado();
    String getDescripcion();
    String getTipo();
}
