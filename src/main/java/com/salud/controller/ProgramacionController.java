package com.salud.controller;

import com.salud.model.entity.Programacion;
import com.salud.model.projection.ProgramacionProjection;
import com.salud.model.request.ProgramacionRequest;
import com.salud.service.ProgramacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/programacion")
public class ProgramacionController {

    @Autowired
    private ProgramacionService programacionService;

    @PostMapping("/findByFiltros")
    public List<ProgramacionProjection> findByFiltros(@RequestBody ProgramacionRequest programacionRequest) {
        return programacionService.findByFiltros(
                programacionRequest.getUsuario(),
                programacionRequest.getIdPaciente()
                );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Programacion> getById(@PathVariable Long id) {

        Programacion programacion = programacionService.getById(id);

        return programacion != null ? ResponseEntity.ok(programacion) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getProgramacionAll/{id}")
    public ResponseEntity<ProgramacionProjection> getProgramacionAll(@PathVariable Long id) {
        ProgramacionProjection programacionProjection = programacionService.getProgramcionAll(id);
        return programacionProjection != null ? ResponseEntity.ok(programacionProjection) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody ProgramacionRequest programacionRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        Programacion programacion = new Programacion();
        programacion.setIdPaciente(programacionRequest.getIdPaciente());
        programacion.setFechaDesde(programacionRequest.getFechaDesde());
        programacion.setFechaHasta(programacionRequest.getFechaHasta());
        programacion.setFec_creacion(new Date());
        programacion.setUser_creacion(user);
        programacionService.create(programacion);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró la programación correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody ProgramacionRequest programacionRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

       Programacion programacion = new Programacion();
        programacion.setId(programacionRequest.getId());
        programacion.setIdPaciente(programacionRequest.getIdPaciente());
        programacion.setFechaDesde(programacionRequest.getFechaDesde());
        programacion.setFechaHasta(programacionRequest.getFechaHasta());
        programacion.setFec_actualizacion(new Date());
        programacion.setUser_actualizacion(user);
        programacionService.update(programacionRequest.getId(), programacion);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó la programación correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        programacionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó la programación correctamente"));
    }
}
