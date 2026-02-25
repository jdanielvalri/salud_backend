package com.salud.service;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Parametro;
import com.salud.repository.CentroRepository;
import com.salud.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    public Parametro getByCodigo(String codigo){
        return parametroRepository.getByCodigo(codigo);
    }

}
