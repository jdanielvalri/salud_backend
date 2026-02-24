package com.salud.service;

import com.salud.model.entity.Aportante;
import com.salud.model.entity.Centro;
import com.salud.repository.AportanteRepository;
import com.salud.repository.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AportanteService {

    @Autowired
    private AportanteRepository aportanteRepository;

    public List<Aportante> getAll(){
        return aportanteRepository.findAll();
    }


}
