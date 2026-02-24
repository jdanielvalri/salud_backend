package com.salud.service;

import com.salud.model.entity.Cita;
import com.salud.model.entity.CitaGasto;
import com.salud.model.projection.CitaGastoProjection;
import com.salud.model.projection.CitaProjection;
import com.salud.repository.CitaArchivoRepository;
import com.salud.repository.CitaGastoRepository;
import com.salud.repository.CitaRepository;
import com.salud.utilitario.ListValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CitaGastoService {

    @Autowired
    private CitaGastoRepository citaGastoRepository;

    public CitaGasto getById(Long id){
        return citaGastoRepository.findById(id).orElse(null);
    }

    public List<CitaGastoProjection> findByCita(Long idCita){
        return citaGastoRepository.findByCita(idCita);
    }

    @Transactional
    public CitaGasto create(CitaGasto citaGasto){
        return citaGastoRepository.save(citaGasto);
    }

    @Transactional
    public CitaGasto update(Long idCitaGasto, CitaGasto actualizado ){
        CitaGasto citaGasto = getById(idCitaGasto);
        if (citaGasto != null) {
            citaGasto.setItem(actualizado.getItem());
            citaGasto.setIdCentro(actualizado.getIdCentro());
            citaGasto.setCantidad(actualizado.getCantidad());
            citaGasto.setFecha(actualizado.getFecha());
            citaGasto.setMonto(actualizado.getMonto());
            citaGasto.setFlgFinanciado(actualizado.getFlgFinanciado());
            citaGasto.setUser_actualizacion(actualizado.getUser_actualizacion());
            citaGasto.setFec_actualizacion(new Date());

            return citaGastoRepository.save(citaGasto);
        }

        return null;
    }

    @Transactional
    public void delete(Long idCitaGasto){
        CitaGasto citaGasto = getById(idCitaGasto);
        citaGastoRepository.delete(citaGasto);
    }

    public double getMontoByCita(Long idCita){
        double montoTotal = 0.0;
        List<CitaGastoProjection> gastos = findByCita(idCita);
        if(gastos!=null){
            for(CitaGastoProjection obj : gastos){
                montoTotal += obj.getMonto();
            }
        }
        return montoTotal;
    }

}
