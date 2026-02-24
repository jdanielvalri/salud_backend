package com.salud.controller;

import com.salud.model.entity.Medico;
import com.salud.model.projection.CitaProjection;
import com.salud.model.request.CitaRequest;
import com.salud.service.CitaService;
import com.salud.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public List<Medico> getAll() {
        return medicoService.getAll();
    }
}
