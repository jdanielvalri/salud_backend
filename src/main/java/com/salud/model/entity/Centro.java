package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "centro")
@Data
@NoArgsConstructor
public class Centro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String tipo;
    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
