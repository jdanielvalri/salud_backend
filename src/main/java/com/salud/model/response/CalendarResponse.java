package com.salud.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarResponse {
    private String id;
    private String title;
    private String descripcion;
    private String date;
    private String start;
    private String end;
    private String backgroundColor;

    private String tipo;

    //datos de cita
    private String especialidad;
    private String centro;
    private String doctor;
    private String estado;
}
