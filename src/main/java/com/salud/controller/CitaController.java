package com.salud.controller;

import com.salud.model.entity.Cita;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.PacienteProjection;
import com.salud.model.request.CitaRequest;
import com.salud.model.request.PacienteRequest;
import com.salud.service.CitaService;
import com.salud.service.PacienteService;
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
@RequestMapping("/api/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Cita> getById(@PathVariable Long id) {
        Cita cita = citaService.getById(id);
        return cita != null ? ResponseEntity.ok(cita) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getCitaAll/{id}")
    public ResponseEntity<CitaProjection> getCitaAll(@PathVariable Long id) {
        CitaProjection cita = citaService.getCitaAll(id);
        return cita != null ? ResponseEntity.ok(cita) : ResponseEntity.notFound().build();
    }

    @PostMapping("/listarPendientes")
    public List<CitaProjection> listarPendientes(@RequestBody CitaRequest citaRequest) {
        System.out.println(citaRequest.getUsuario());
        System.out.println(citaRequest.getDesde());
        System.out.println(citaRequest.getHasta());
        System.out.println(citaRequest.getIdPaciente());
        System.out.println(citaRequest.getIdEspecialidad());
        System.out.println(citaRequest.getEstado());

        Date fecIni = Date.from(citaRequest.getDesde().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fecFin = Date.from(citaRequest.getHasta().atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("fecIni " + fecIni);
        System.out.println("fecFin " + fecFin);

        return citaService.findByFiltros(citaRequest.getUsuario(), fecIni,
                fecFin, citaRequest.getIdPaciente(), citaRequest.getIdEspecialidad(),
                citaRequest.getIdMedico(), "PEND");
    }

    @PostMapping("/listarHistorial")
    public List<CitaProjection> listarHistorial(@RequestBody CitaRequest citaRequest) {
        Date fecIni = Date.from(citaRequest.getDesde().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fecFin = Date.from(citaRequest.getHasta().atStartOfDay(ZoneId.systemDefault()).toInstant());

        return citaService.findByFiltros(citaRequest.getUsuario(), fecIni,
                fecFin,citaRequest.getIdPaciente(), citaRequest.getIdEspecialidad(),
                citaRequest.getIdMedico(), citaRequest.getEstado());
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody CitaRequest citaRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        Cita cita = new Cita();
        cita.setIdPaciente(citaRequest.getIdPaciente());
        cita.setIdCentro(citaRequest.getIdCentro());
        cita.setIdMedico(citaRequest.getIdMedico());
        cita.setIdEspecialidad(citaRequest.getIdEspecialidad());
        cita.setFecha(citaRequest.getFecha());
        cita.setHora(citaRequest.getHora());
        cita.setEstado(citaRequest.getEstado());
        cita.setComentario(citaRequest.getComentario());
        cita.setFec_creacion(new Date());
        cita.setUser_creacion(user);

        citaService.create(cita);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se registró la cita correctamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody CitaRequest citaRequest) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        Cita cita = new Cita();
        cita.setId(citaRequest.getIdCita());
        cita.setIdPaciente(citaRequest.getIdPaciente());
        cita.setIdCentro(citaRequest.getIdCentro());
        cita.setIdMedico(citaRequest.getIdMedico());
        cita.setIdEspecialidad(citaRequest.getIdEspecialidad());
        cita.setFecha(citaRequest.getFecha());
        cita.setHora(citaRequest.getHora());
        cita.setEstado(citaRequest.getEstado());
        cita.setComentario(citaRequest.getComentario());
        cita.setFec_actualizacion(new Date());
        cita.setUser_actualizacion(user);

        citaService.update(cita.getId(), cita);

        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se actualizó la cita correctamente"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
        citaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK) // 201
                .body(Map.of("message", "Se eliminó la cita correctamente"));
    }
}
