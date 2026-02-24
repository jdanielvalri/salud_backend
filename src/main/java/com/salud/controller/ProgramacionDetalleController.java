package com.salud.controller;

import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.entity.ProgramacionDetalle;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.request.FinanciamientoDetalleRequest;
import com.salud.model.request.ProgramacionDetalleRequest;
import com.salud.service.FinanciamientoDetalleService;
import com.salud.service.ProgramacionDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/programacion_detalle")
public class ProgramacionDetalleController {

    @Autowired
    private ProgramacionDetalleService programacionDetalleService;

    @PostMapping("/findByIdProgramacion")
    public List<ProgramacionDetalle> findByIdProgramacion(@RequestBody ProgramacionDetalleRequest programacionDetalleRequest) {

        return programacionDetalleService.getByIdProgramacion(programacionDetalleRequest.getIdProgramacion()
                );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProgramacionDetalle> getById(@PathVariable Long id) {
        ProgramacionDetalle det = programacionDetalleService.getById(id);
        return det != null ? ResponseEntity.ok(det) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody ProgramacionDetalleRequest programacionDetalleRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        ProgramacionDetalle programacionDetalle= new ProgramacionDetalle();
        programacionDetalle.setIdProgramacion(programacionDetalleRequest.getIdProgramacion());
        programacionDetalle.setTarea(programacionDetalleRequest.getTarea());
        programacionDetalle.setOrden(programacionDetalleRequest.getOrden());
        programacionDetalle.setFec_creacion(new Date());
        programacionDetalle.setUser_creacion(user);
        programacionDetalleService.create(programacionDetalle);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró la tarea correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody ProgramacionDetalleRequest programacionDetalleRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        ProgramacionDetalle programacionDetalle = new ProgramacionDetalle();
        programacionDetalle.setId(programacionDetalleRequest.getId());
        programacionDetalle.setIdProgramacion(programacionDetalleRequest.getIdProgramacion());
        programacionDetalle.setTarea(programacionDetalleRequest.getTarea());
        programacionDetalle.setOrden(programacionDetalleRequest.getOrden());
        programacionDetalle.setFec_actualizacion(new Date());
        programacionDetalle.setUser_actualizacion(user);

        programacionDetalleService.update(programacionDetalleRequest.getId(), programacionDetalle);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó la tarea correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        programacionDetalleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó la tarea correctamente"));
    }
}
