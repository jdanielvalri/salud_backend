package com.salud.controller;

import com.salud.model.entity.Paciente;
import com.salud.model.projection.PacienteProjection;
import com.salud.model.request.PacienteRequest;
import com.salud.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/listarByUsuario")
    public List<PacienteProjection> listarByUsuario(@RequestBody PacienteRequest pacienteRequest) {
        System.out.println(pacienteRequest.getUsuario());
        return pacienteService.findByUsuario(pacienteRequest.getUsuario());
    }
}
