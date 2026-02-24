package com.salud.controller;

import com.salud.model.entity.Financiamiento;
import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.entity.FinanciamientoPaciente;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.projection.FinanciamientoPacienteProjection;
import com.salud.model.request.FinanciamientoDetalleRequest;
import com.salud.model.request.FinanciamientoPacienteRequest;
import com.salud.service.FinanciamientoDetalleService;
import com.salud.service.FinanciamientoPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/financiamiento_paciente")
public class FinanciamientoPacienteController {

    @Autowired
    private FinanciamientoPacienteService financiamientoPacienteService;

    @PostMapping("/findByFiltros")
    public List<FinanciamientoPacienteProjection> findByFiltros(@RequestBody FinanciamientoPacienteRequest financiamientoPacienteRequest) {

        return financiamientoPacienteService.findByIdFinanciamiento(financiamientoPacienteRequest.getIdFinanciamiento()
                );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<FinanciamientoPaciente> getById(@PathVariable Long id) {
        FinanciamientoPaciente financiamientoPaciente = financiamientoPacienteService.getById(id);
        return financiamientoPaciente != null ? ResponseEntity.ok(financiamientoPaciente) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody FinanciamientoPacienteRequest request) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        FinanciamientoPaciente financiamientoPaciente = financiamientoPacienteService.getByIdFinanciamientoAndIdPaciente(
                request.getIdFinanciamiento(), request.getIdPaciente());

        if(financiamientoPaciente==null){
            financiamientoPaciente = new FinanciamientoPaciente();
            financiamientoPaciente.setIdFinanciamiento(request.getIdFinanciamiento());
            financiamientoPaciente.setIdPaciente(request.getIdPaciente());
            financiamientoPaciente.setFec_creacion(new Date());
            financiamientoPaciente.setUser_creacion(user);

            financiamientoPacienteService.create(financiamientoPaciente);

        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                    .body(Map.of("message", "Ya existe un registro con los datos seleccionados"));

        }

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró el paciente correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody FinanciamientoPacienteRequest request) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        FinanciamientoPaciente financiamientoPaciente = financiamientoPacienteService.getByIdFinanciamientoAndIdPaciente(request.getIdFinanciamiento(),
                request.getIdPaciente());
        if(financiamientoPaciente!=null){
            if(financiamientoPaciente.getId()==request.getId()){
                financiamientoPaciente = null;
            }
        }

        if(financiamientoPaciente==null){
            financiamientoPaciente = new FinanciamientoPaciente();
            financiamientoPaciente.setId(request.getId());
            financiamientoPaciente.setIdFinanciamiento(request.getIdFinanciamiento());
            financiamientoPaciente.setIdPaciente(request.getIdPaciente());
            financiamientoPaciente.setFec_actualizacion(new Date());
            financiamientoPaciente.setUser_actualizacion(user);
            financiamientoPacienteService.update(financiamientoPaciente.getId(), financiamientoPaciente);
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                    .body(Map.of("message", "Ya existe un registro con los datos seleccionados"));

        }


        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó el paciente correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        financiamientoPacienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó el paciente correctamente"));
    }
}
