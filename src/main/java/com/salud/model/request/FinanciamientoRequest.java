package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class FinanciamientoRequest {
    private Long id;
    private Integer anio;
    private String mes;

}
