package com.salud.service;

import com.salud.model.entity.Financiamiento;
import com.salud.model.entity.Programacion;
import com.salud.model.projection.FinanciamientoProjection;
import com.salud.model.projection.ProgramacionProjection;
import com.salud.repository.FinanciamientoDetalleRepository;
import com.salud.repository.FinanciamientoPacienteRepository;
import com.salud.repository.FinanciamientoRepository;
import com.salud.repository.ProgramacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProgramacionService {

    @Autowired
    private ProgramacionRepository programacionRepository;

    public List<ProgramacionProjection> findByFiltros(String usuario,Long idPaciente){
        return programacionRepository.findByFiltros(usuario,idPaciente);
    }

    public Programacion getById(Long id){
        return programacionRepository.findById(id).orElse(null);
    }

    public ProgramacionProjection getProgramcionAll(Long id){
        return programacionRepository.getProgramacionAll(id);
    }

    @Transactional
    public Programacion create(Programacion programacion){
        return programacionRepository.save(programacion);
    }

    @Transactional
    public Programacion update(Long id, Programacion actualizado ){
        Programacion programacion = getById(id);
        if (programacion != null) {
            programacion.setIdPaciente(actualizado.getIdPaciente());
            programacion.setFechaDesde(actualizado.getFechaDesde());
            programacion.setFechaHasta(actualizado.getFechaHasta());
            programacion.setUser_actualizacion(actualizado.getUser_actualizacion());
            programacion.setFec_actualizacion(new Date());

            return programacionRepository.save(programacion);
        }

        return null;
    }

    @Transactional
    public void delete(Long id){

        programacionRepository.deleteById(id);
    }

}
