package com.salud.controller;

import com.salud.model.entity.Financiamiento;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.FinanciamientoProjection;
import com.salud.model.request.FinanciamientoRequest;
import com.salud.service.FinanciamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/financiamiento")
public class FinanciamientoController {

    @Autowired
    private FinanciamientoService financiamientoService;

    @PostMapping("/findByFiltros")
    public List<FinanciamientoProjection> findByFiltros(@RequestBody FinanciamientoRequest financiamientoRequest) {
        return financiamientoService.findByFiltros(
                financiamientoRequest.getAnio(),financiamientoRequest.getMes()
                );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Financiamiento> getById(@PathVariable Long id) {

        Financiamiento financiamiento = financiamientoService.getById(id);

        return financiamiento != null ? ResponseEntity.ok(financiamiento) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getFinanciamientoAll/{id}")
    public ResponseEntity<FinanciamientoProjection> getFinanciamientoAll(@PathVariable Long id) {
        FinanciamientoProjection financiamientoProjection = financiamientoService.getFinanciamientoAll(id);
        return financiamientoProjection != null ? ResponseEntity.ok(financiamientoProjection) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody FinanciamientoRequest financiamientoRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("anio " + financiamientoRequest.getAnio());
        System.out.println("mes " + financiamientoRequest.getMes());

        Financiamiento financiamiento = financiamientoService.getByAnioAndMes(financiamientoRequest.getAnio(),
                financiamientoRequest.getMes());

        if(financiamiento == null){
            financiamiento = new Financiamiento();
            financiamiento.setAnio(financiamientoRequest.getAnio());
            financiamiento.setMes(financiamientoRequest.getMes());
            financiamiento.setFec_creacion(new Date());
            financiamiento.setUser_creacion(user);
            financiamientoService.create(financiamiento);
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                    .body(Map.of("message", "Ya existe un registro con los datos seleccionados"));
        }

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró el financiamiento correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody FinanciamientoRequest financiamientoRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        Financiamiento financiamiento = financiamientoService.getByAnioAndMes(financiamientoRequest.getAnio(),
                financiamientoRequest.getMes());
        if(financiamiento!=null){
            if(financiamiento.getId()==financiamientoRequest.getId()){
                financiamiento = null;
            }
        }

        if(financiamiento==null){
            financiamiento = new Financiamiento();
            financiamiento.setId(financiamientoRequest.getId());
            financiamiento.setAnio(financiamientoRequest.getAnio());
            financiamiento.setMes(financiamientoRequest.getMes());
            financiamiento.setFec_actualizacion(new Date());
            financiamiento.setUser_actualizacion(user);
            financiamientoService.update(financiamientoRequest.getId(), financiamiento);

        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                    .body(Map.of("message", "Ya existe un registro con los datos seleccionados"));

        }
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó el financiamiento correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        financiamientoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó el financiamiento correctamente"));
    }
}
