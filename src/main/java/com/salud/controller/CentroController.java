package com.salud.controller;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Medico;
import com.salud.service.CentroService;
import com.salud.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/centro")
public class CentroController {

    @Autowired
    private CentroService centroService;

    @GetMapping("/listar")
    public List<Centro> getAll() {
        return centroService.getAll();
    }
}
