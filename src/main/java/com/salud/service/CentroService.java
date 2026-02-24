package com.salud.service;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Paciente;
import com.salud.model.projection.PacienteProjection;
import com.salud.repository.CentroRepository;
import com.salud.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroService {

    @Autowired
    private CentroRepository centroRepository;

    public List<Centro> getAll(){
        return centroRepository.findAll();
    }

    public Centro getById(Long id){
        return centroRepository.findById(id).orElse(null);
    }

    public Centro create(Centro centro){
        return centroRepository.save(centro);
    }

}
