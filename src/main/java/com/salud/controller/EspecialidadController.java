package com.salud.controller;

import com.salud.model.entity.Especialidad;
import com.salud.model.entity.Medico;
import com.salud.service.EspecialidadService;
import com.salud.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/listar")
    public List<Especialidad> getAll() {
        return especialidadService.getAll();
    }
}
