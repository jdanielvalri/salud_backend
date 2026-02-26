package com.salud.service;

import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.entity.ProgramacionDetalle;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.repository.FinanciamientoDetalleRepository;
import com.salud.repository.ProgramacionDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProgramacionDetalleService {

    @Autowired
    private ProgramacionDetalleRepository programacionDetalleRepository;

    public ProgramacionDetalle getById(Long id){
        return programacionDetalleRepository.findById(id).orElse(null);
    }

    public List<ProgramacionDetalle> getByIdProgramacion(Long idProgramacion){
        return programacionDetalleRepository.getByIdProgramacionOrderByOrdenAsc(idProgramacion);
    }

    @Transactional
    public ProgramacionDetalle create(ProgramacionDetalle programacionDetalle){
        return programacionDetalleRepository.save(programacionDetalle);
    }

    @Transactional
    public ProgramacionDetalle update(Long id, ProgramacionDetalle actualizado ){
        ProgramacionDetalle progDet = getById(id);
        if (progDet != null) {
            progDet.setIdProgramacion(actualizado.getIdProgramacion());
            progDet.setTarea(actualizado.getTarea());
            progDet.setOrden(actualizado.getOrden());
            progDet.setUser_actualizacion(actualizado.getUser_actualizacion());
            progDet.setFec_actualizacion(new Date());

            return programacionDetalleRepository.save(progDet);
        }

        return null;
    }

    @Transactional
    public void delete(Long id){
        programacionDetalleRepository.deleteById(id);
    }

}
