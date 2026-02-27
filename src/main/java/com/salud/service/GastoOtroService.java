package com.salud.service;

import com.salud.model.entity.Cita;
import com.salud.model.entity.GastoOtro;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.GastoOtroProjection;
import com.salud.repository.CitaArchivoRepository;
import com.salud.repository.CitaGastoRepository;
import com.salud.repository.CitaRepository;
import com.salud.repository.GastoOtroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoOtroService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private GastoOtroRepository gastoOtroRepository;

    public GastoOtro getById(Long id){

        GastoOtro gastoOtro = gastoOtroRepository.findById(id).orElse(null);

        return gastoOtro;
    }

    @Transactional
    public GastoOtro create(GastoOtro gastoOtro){
        return gastoOtroRepository.save(gastoOtro);
    }

    @Transactional
    public GastoOtro update(Long idGastoOtro, GastoOtro actualizado ){
        GastoOtro gastoOtro = getById(idGastoOtro);
        if (gastoOtro != null) {
            gastoOtro.setIdPaciente(actualizado.getIdPaciente());
            gastoOtro.setIdCentro(actualizado.getIdCentro());
            gastoOtro.setIdEspecialidad(actualizado.getIdEspecialidad());
            gastoOtro.setFecha(actualizado.getFecha());
            gastoOtro.setMonto(actualizado.getMonto());
            gastoOtro.setDescripcion(actualizado.getDescripcion());
            gastoOtro.setFlgFinanciado(actualizado.getFlgFinanciado());
            gastoOtro.setUser_actualizacion(actualizado.getUser_actualizacion());
            gastoOtro.setFec_actualizacion(new Date());

            return gastoOtroRepository.save(gastoOtro);
        }

        return null;
    }

    @Transactional
    public void delete(Long idGastoOtro){
        gastoOtroRepository.deleteById(idGastoOtro);
    }

    public List<GastoOtroProjection> findByFiltros(String usuario, Date desde, Date hasta,
                                                   Long idPaciente, Long idEspecialidad, String tipo){
        List<GastoOtroProjection> datos = new ArrayList<GastoOtroProjection>();

        if(tipo==null){
            tipo = "T";
        }

        if("C".equals(tipo) || "T".equals(tipo)){ //gastos cita
            datos.addAll(citaRepository.findByFiltrosGastoOtro(usuario,desde,hasta,
                    idPaciente,idEspecialidad));
            datos = datos.stream()
                    .filter(gasto -> gasto.getMontoFinanciado()>0)
                    .collect(Collectors.toList());
        }
        if("O".equals(tipo) || "T".equals(tipo)){ //gastos cita
            datos.addAll(gastoOtroRepository.findByFiltros(usuario,desde,hasta,
                    idPaciente,idEspecialidad));
        }

        return datos;
    }

}
