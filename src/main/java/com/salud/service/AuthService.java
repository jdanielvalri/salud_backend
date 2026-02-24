package com.salud.service;

import com.salud.model.entity.Usuario;
import com.salud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario autenticar(String usuario, String pass, String estado) {
        Usuario u = usuarioRepository.findByUsuarioAndPassAndEstado(usuario,pass,estado);

        if(u!=null) {
            //if (!encoder.matches(password, u.getPassword())) {
            if (!pass.equals(u.getPass())) {
                u = null;
            }
        }

        return u;
    }

}
