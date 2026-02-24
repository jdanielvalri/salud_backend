package com.salud.service;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Medico;
import com.salud.repository.CentroRepository;
import com.salud.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> getAll(){
        return medicoRepository.findAll();
    }

    public Medico getById(Long id){
        return medicoRepository.findById(id).orElse(null);
    }

    public Medico create(Medico medico){
        return medicoRepository.save(medico);
    }

}
