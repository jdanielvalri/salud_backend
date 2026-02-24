package com.salud.service;

import com.salud.model.entity.Paciente;
import com.salud.model.entity.Usuario;
import com.salud.model.projection.PacienteProjection;
import com.salud.repository.PacienteRepository;
import com.salud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAll(){
        return pacienteRepository.findAll();
    }

    public Paciente getById(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente create(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public List<PacienteProjection> findByUsuario(String usuario){
        return pacienteRepository.findByUsuario(usuario);
    }


}
