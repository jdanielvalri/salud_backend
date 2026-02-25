package com.salud.controller;

import com.salud.model.entity.Cita;
import com.salud.model.entity.GastoOtro;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.GastoOtroProjection;
import com.salud.model.request.CitaRequest;
import com.salud.model.request.GastoOtroRequest;
import com.salud.service.CitaService;
import com.salud.service.GastoOtroService;
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
@RequestMapping("/api/gasto_otro")
public class GastoOtroController {

    @Autowired
    private GastoOtroService gastoOtroService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<GastoOtro> getById(@PathVariable Long id) {
        GastoOtro gastoOtro = gastoOtroService.getById(id);
        return gastoOtro != null ? ResponseEntity.ok(gastoOtro) : ResponseEntity.notFound().build();
    }

    @PostMapping("/findByFiltros")
    public List<GastoOtroProjection> findByFiltros(@RequestBody GastoOtroRequest gastoOtroRequest) {

        Date fecIni = Date.from(gastoOtroRequest.getDesde().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fecFin = Date.from(gastoOtroRequest.getHasta().atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("fecIni " + fecIni);
        System.out.println("fecFin " + fecFin);

        return gastoOtroService.findByFiltros(gastoOtroRequest.getUsuario(), fecIni,
                fecFin, gastoOtroRequest.getIdPaciente(), gastoOtroRequest.getIdEspecialidad(),
                gastoOtroRequest.getTipo());
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody GastoOtroRequest gastoOtroRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        GastoOtro gastoOtro = new GastoOtro();
        gastoOtro.setIdPaciente(gastoOtroRequest.getIdPaciente());
        gastoOtro.setIdCentro(gastoOtroRequest.getIdCentro());
        gastoOtro.setIdEspecialidad(gastoOtroRequest.getIdEspecialidad());
        gastoOtro.setFecha(gastoOtroRequest.getFecha());
        gastoOtro.setMonto(gastoOtroRequest.getMonto());
        gastoOtro.setFlgFinanciado(gastoOtroRequest.getFlgFinanciado());
        gastoOtro.setDescripcion(gastoOtroRequest.getDescripcion());
        gastoOtro.setFec_creacion(new Date());
        gastoOtro.setUser_creacion(user);

        gastoOtroService.create(gastoOtro);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró el gasto correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody GastoOtroRequest gastoOtroRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        GastoOtro gastoOtro = new GastoOtro();
        gastoOtro.setId(gastoOtroRequest.getId());
        gastoOtro.setIdPaciente(gastoOtroRequest.getIdPaciente());
        gastoOtro.setIdCentro(gastoOtroRequest.getIdCentro());
        gastoOtro.setIdEspecialidad(gastoOtroRequest.getIdEspecialidad());
        gastoOtro.setFecha(gastoOtroRequest.getFecha());
        gastoOtro.setMonto(gastoOtroRequest.getMonto());
        gastoOtro.setDescripcion(gastoOtroRequest.getDescripcion());
        gastoOtro.setFlgFinanciado(gastoOtroRequest.getFlgFinanciado());
        gastoOtro.setFec_actualizacion(new Date());
        gastoOtro.setUser_actualizacion(user);

        gastoOtroService.update(gastoOtro.getId(), gastoOtro);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó el gasto correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        gastoOtroService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó el gasto correctamente"));
    }

}
