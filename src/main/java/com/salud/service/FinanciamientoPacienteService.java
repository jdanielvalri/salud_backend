package com.salud.service;

import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.entity.FinanciamientoPaciente;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.projection.FinanciamientoPacienteProjection;
import com.salud.repository.FinanciamientoDetalleRepository;
import com.salud.repository.FinanciamientoPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FinanciamientoPacienteService {

    @Autowired
    private FinanciamientoPacienteRepository financiamientoPacienteRepository;

    public FinanciamientoPaciente getById(Long id){
        return financiamientoPacienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public FinanciamientoPaciente create(FinanciamientoPaciente financiamientoPaciente){
        return financiamientoPacienteRepository.save(financiamientoPaciente);
    }

    @Transactional
    public FinanciamientoPaciente update(Long id, FinanciamientoPaciente actualizado ){
        FinanciamientoPaciente financiamientoPaciente = getById(id);
        if (financiamientoPaciente != null) {
            financiamientoPaciente.setIdPaciente(actualizado.getIdPaciente());
            financiamientoPaciente.setUser_actualizacion(actualizado.getUser_actualizacion());
            financiamientoPaciente.setFec_actualizacion(new Date());

            return financiamientoPacienteRepository.save(financiamientoPaciente);
        }

        return null;
    }

    @Transactional
    public void delete(Long id){
        financiamientoPacienteRepository.deleteById(id);
    }

    public FinanciamientoPaciente getByIdFinanciamientoAndIdPaciente(Long idFinanciamiento, Long idPaciente){
        return financiamientoPacienteRepository.getByIdFinanciamientoAndIdPaciente(idFinanciamiento, idPaciente);
    }

    public List<FinanciamientoPacienteProjection> findByIdFinanciamiento( Long idFinanciamiento){
        return financiamientoPacienteRepository.findByIdFinanciamiento(idFinanciamiento);
    }
}
