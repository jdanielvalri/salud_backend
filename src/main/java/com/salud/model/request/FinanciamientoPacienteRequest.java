package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinanciamientoPacienteRequest {
    private Long id;
    private Long idPaciente;
    private Long idFinanciamiento;

}
