package com.salud.model.projection;

public interface FinanciamientoDetalleProjection {

    Long getId();
    Long getIdFinanciamiento();
    Long getIdAportante();
    Integer getAnio();
    String getMes();
    String getNombreMes();
    String getNombreAportante();
    double getEsperado();
    double getEntregado();
    double getPendiente();
}
