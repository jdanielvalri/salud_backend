package com.salud.controller;

import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.request.FinanciamientoDetalleRequest;
import com.salud.service.FinanciamientoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/financiamiento_detalle")
public class FinanciamientoDetalleController {

    @Autowired
    private FinanciamientoDetalleService financiamientoDetalleService;

    @PostMapping("/findByFiltros")
    public List<FinanciamientoDetalleProjection> findByFiltros(@RequestBody FinanciamientoDetalleRequest financiamientoDetalleRequest) {

        return financiamientoDetalleService.findByFiltros(financiamientoDetalleRequest.getIdFinanciamiento(),financiamientoDetalleRequest.getIdAportante()
                );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<FinanciamientoDetalle> getById(@PathVariable Long id) {
        FinanciamientoDetalle det = financiamientoDetalleService.getById(id);
        return det != null ? ResponseEntity.ok(det) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody FinanciamientoDetalleRequest financiamientoDetalleRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        FinanciamientoDetalle financiamientoDetalle = financiamientoDetalleService.getByIdFinanciamientoAndIdAportante(
                financiamientoDetalleRequest.getIdFinanciamiento(), financiamientoDetalleRequest.getIdAportante());

        if(financiamientoDetalle==null){
            financiamientoDetalle = new FinanciamientoDetalle();
            financiamientoDetalle.setIdFinanciamiento(financiamientoDetalleRequest.getIdFinanciamiento());
            financiamientoDetalle.setIdAportante(financiamientoDetalleRequest.getIdAportante());
            financiamientoDetalle.setEntregado(financiamientoDetalleRequest.getEntregado());
            financiamientoDetalle.setEsperado(financiamientoDetalleRequest.getEsperado());
            financiamientoDetalle.setFec_creacion(new Date());
            financiamientoDetalle.setUser_creacion(user);

            financiamientoDetalleService.create(financiamientoDetalle);

        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                    .body(Map.of("message", "Ya existe un registro con los datos seleccionados"));

        }

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró el financiamiento correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody FinanciamientoDetalleRequest financiamientoDetalleRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        FinanciamientoDetalle financiamientoDetalle = new FinanciamientoDetalle();
        financiamientoDetalle.setId(financiamientoDetalleRequest.getId());
        financiamientoDetalle.setIdFinanciamiento(financiamientoDetalleRequest.getIdFinanciamiento());
        financiamientoDetalle.setIdAportante(financiamientoDetalleRequest.getIdAportante());
        financiamientoDetalle.setEsperado(financiamientoDetalleRequest.getEsperado());
        financiamientoDetalle.setEntregado(financiamientoDetalleRequest.getEntregado());
        financiamientoDetalle.setFec_actualizacion(new Date());
        financiamientoDetalle.setUser_actualizacion(user);

        financiamientoDetalleService.update(financiamientoDetalleRequest.getId(), financiamientoDetalle);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó el financiamiento correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        financiamientoDetalleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó el financiamiento correctamente"));
    }
}
