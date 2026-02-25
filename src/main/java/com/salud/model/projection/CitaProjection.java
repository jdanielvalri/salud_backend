package com.salud.model.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface CitaProjection {

    Long getIdCita();
    Long getIdPaciente();
    Long getIdEspecialidad();
    Long getIdCentro();
    Long getIdMedico();
    String getNombrePaciente();
    String getNombreEspecialidad();
    String getNombreCentro();
    String getNombreMedico();
    Date getFecha();
    String getFechaCadena();
    LocalDateTime getHora();
    double getMontoTotal();
    String getEstado();
    String getNombreEstado();
    String getComentario();
}
