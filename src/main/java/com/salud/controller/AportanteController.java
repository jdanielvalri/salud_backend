package com.salud.controller;

import com.salud.model.entity.Aportante;
import com.salud.model.entity.Centro;
import com.salud.service.AportanteService;
import com.salud.service.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aportante")
public class AportanteController {

    @Autowired
    private AportanteService aportanteService;

    @GetMapping("/listar")
    public List<Aportante> getAll() {
        return aportanteService.getAll();
    }
}
