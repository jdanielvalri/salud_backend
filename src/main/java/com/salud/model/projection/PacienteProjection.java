package com.salud.model.projection;

import java.util.Date;

public interface PacienteProjection {

    Long getIdPaciente();
    Long getIdUsuario();
    String getNombre();
    String getApePaterno();
    String getApeMaterno();
    String getDocumento();
    String getSexo();
    Date getFecNacimiento();
}
