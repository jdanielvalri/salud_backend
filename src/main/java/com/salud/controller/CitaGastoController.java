package com.salud.controller;

import com.salud.model.entity.Cita;
import com.salud.model.entity.CitaGasto;
import com.salud.model.projection.CitaGastoProjection;
import com.salud.model.projection.CitaProjection;
import com.salud.model.request.CitaGastoRequest;
import com.salud.model.request.CitaRequest;
import com.salud.service.CitaGastoService;
import com.salud.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citaGasto")
public class CitaGastoController {

    @Autowired
    private CitaGastoService citaGastoService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<CitaGasto> getById(@PathVariable Long id) {
        CitaGasto citaGasto = citaGastoService.getById(id);
        return citaGasto != null ? ResponseEntity.ok(citaGasto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/findByCita")
    public List<CitaGastoProjection> findByCita(@RequestBody CitaGastoRequest citaGastoRequest) {
        return citaGastoService.findByCita(citaGastoRequest.getIdCita());
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody CitaGastoRequest citaGastoRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        CitaGasto citaGasto = new CitaGasto();
        citaGasto.setIdCita(citaGastoRequest.getIdCita());
        citaGasto.setIdCentro(citaGastoRequest.getIdCentro());
        citaGasto.setItem(citaGastoRequest.getItem());
        citaGasto.setCantidad(citaGastoRequest.getCantidad());
        citaGasto.setFecha(citaGastoRequest.getFecha());
        citaGasto.setMonto(citaGastoRequest.getMonto());
        citaGasto.setFlgFinanciado(citaGastoRequest.getFlgFinanciado());
        citaGasto.setFec_creacion(new Date());
        citaGasto.setUser_creacion(user);

        citaGastoService.create(citaGasto);

        //actualizamos el

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró el gasto correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody CitaGastoRequest citaGastoRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        CitaGasto citaGasto = new CitaGasto();
        citaGasto.setId(citaGastoRequest.getId());
        citaGasto.setIdCita(citaGastoRequest.getIdCita());
        citaGasto.setIdCentro(citaGastoRequest.getIdCentro());
        citaGasto.setItem(citaGastoRequest.getItem());
        citaGasto.setCantidad(citaGastoRequest.getCantidad());
        citaGasto.setFecha(citaGastoRequest.getFecha());
        citaGasto.setMonto(citaGastoRequest.getMonto());
        citaGasto.setFlgFinanciado(citaGastoRequest.getFlgFinanciado());
        citaGasto.setFec_actualizacion(new Date());
        citaGasto.setUser_actualizacion(user);

        citaGastoService.update(citaGasto.getId(), citaGasto);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó el gasto correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        citaGastoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó el gasto correctamente"));
    }

}
