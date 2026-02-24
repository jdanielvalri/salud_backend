package com.salud.model.projection;

import java.util.Date;

public interface ProgramacionProjection {

    Long getId();
    Long getIdPaciente();
    Date getFechaDesde();
    Date getFechaHasta();
    String getNombrePaciente();
}
