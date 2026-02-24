package com.salud.service;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Financiamiento;
import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.FinanciamientoProjection;
import com.salud.repository.CentroRepository;
import com.salud.repository.FinanciamientoDetalleRepository;
import com.salud.repository.FinanciamientoPacienteRepository;
import com.salud.repository.FinanciamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class FinanciamientoService {

    @Autowired
    private FinanciamientoRepository financiamientoRepository;

    @Autowired
    private FinanciamientoDetalleRepository financiamientoDetalleRepository;

    @Autowired
    private FinanciamientoPacienteRepository financiamientoPacienteRepository;

    public List<FinanciamientoProjection> findByFiltros(Integer anio, String mes){
        return financiamientoRepository.findByFiltros(anio,mes);
    }

    public Financiamiento getById(Long id){
        return financiamientoRepository.findById(id).orElse(null);
    }

    public FinanciamientoProjection getFinanciamientoAll(Long id){
        return financiamientoRepository.getFinanciamientoAll(id);
    }

    @Transactional
    public Financiamiento create(Financiamiento financiamiento){
        return financiamientoRepository.save(financiamiento);
    }

    @Transactional
    public Financiamiento update(Long id, Financiamiento actualizado ){
        Financiamiento financiamiento = getById(id);
        if (financiamiento != null) {
            financiamiento.setAnio(actualizado.getAnio());
            financiamiento.setMes(actualizado.getMes());
            financiamiento.setUser_actualizacion(actualizado.getUser_actualizacion());
            financiamiento.setFec_actualizacion(new Date());

            return financiamientoRepository.save(financiamiento);
        }

        return null;
    }

    @Transactional
    public void delete(Long id){
        financiamientoPacienteRepository.deleteByIdFinanciamiento(id);
        financiamientoDetalleRepository.deleteByIdFinanciamiento(id);
        financiamientoRepository.deleteById(id);
    }

    public Financiamiento getByAnioAndMes(Integer anio, String mes){
        return financiamientoRepository.getByAnioAndMes(anio,mes);
    }
}
