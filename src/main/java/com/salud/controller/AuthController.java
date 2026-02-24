package com.salud.controller;

import com.salud.model.entity.Usuario;
import com.salud.seguridad.TokenResponse;
import com.salud.seguridad.UserCredentials;
import com.salud.service.AuthService;
import com.salud.utilitario.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    private AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody UserCredentials credentials) {
        // 🔸 Aquí validarías el usuario contra la BD
        Usuario usuario = authService.autenticar(credentials.getUsername(),
                credentials.getPassword(), credentials.getEstado());
        if (usuario!=null) {
            String token = jwtUtil.generateToken(credentials.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials credentials) {
        // 🔸 Aquí validarías el usuario contra la BD
        Usuario usuario = authService.autenticar(credentials.getUsername(),
                credentials.getPassword(), credentials.getEstado());
        if (usuario!=null) {
            String token = jwtUtil.generateToken(credentials.getUsername());
            // 🔐 Aquí validas si existe en DB o lo creas
            Map<String, Object> user = Map.of(
                    "user", usuario.getUsuario(),
                    "name", usuario.getNombre(),
                    "perfil", usuario.getPerfil()
            );

            return ResponseEntity.ok(Map.of(
                    "user", user,
                    "token", token
            ));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }

}

