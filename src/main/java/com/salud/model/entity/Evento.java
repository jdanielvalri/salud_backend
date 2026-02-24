package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "evento")
@Data
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Date desde;
    private Date hasta;
    private LocalDateTime hora;
    private String tipo;
    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
