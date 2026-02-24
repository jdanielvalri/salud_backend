package com.salud.service;

import com.salud.model.entity.Especialidad;
import com.salud.model.entity.Medico;
import com.salud.repository.EspecialidadRepository;
import com.salud.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> getAll(){
        return especialidadRepository.findAll();
    }

    public Especialidad getById(Long id){
        return especialidadRepository.findById(id).orElse(null);
    }

    public Especialidad create(Especialidad especialidad){
        return especialidadRepository.save(especialidad);
    }

}
