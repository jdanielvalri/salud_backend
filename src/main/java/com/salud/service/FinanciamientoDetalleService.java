package com.salud.service;

import com.salud.model.entity.Cita;
import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.projection.FinanciamientoProjection;
import com.salud.repository.FinanciamientoDetalleRepository;
import com.salud.repository.FinanciamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FinanciamientoDetalleService {

    @Autowired
    private FinanciamientoDetalleRepository financiamientoDetalleRepository;

    public FinanciamientoDetalle getById(Long id){
        return financiamientoDetalleRepository.findById(id).orElse(null);
    }

    @Transactional
    public FinanciamientoDetalle create(FinanciamientoDetalle financiamientoDetalle){
        return financiamientoDetalleRepository.save(financiamientoDetalle);
    }

    @Transactional
    public FinanciamientoDetalle update(Long id, FinanciamientoDetalle actualizado ){
        FinanciamientoDetalle finaDet = getById(id);
        if (finaDet != null) {
            finaDet.setIdAportante(actualizado.getIdAportante());
            finaDet.setEsperado(actualizado.getEsperado());
            finaDet.setEntregado(actualizado.getEntregado());
            finaDet.setUser_actualizacion(actualizado.getUser_actualizacion());
            finaDet.setFec_actualizacion(new Date());

            return financiamientoDetalleRepository.save(finaDet);
        }

        return null;
    }

    @Transactional
    public void delete(Long id){
        financiamientoDetalleRepository.deleteById(id);
    }

    public FinanciamientoDetalle getByIdFinanciamientoAndIdAportante(Long idFinanciamiento, Long idAportante){
        return financiamientoDetalleRepository.getByIdFinanciamientoAndIdAportante(idFinanciamiento, idAportante);
    }

    public List<FinanciamientoDetalleProjection> findByFiltros(Long idFinanciamiento, Long idAportante){
        return financiamientoDetalleRepository.findByFiltros(idFinanciamiento, idAportante);
    }
}
