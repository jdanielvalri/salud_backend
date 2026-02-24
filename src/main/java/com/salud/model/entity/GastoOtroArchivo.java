package com.salud.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "gasto_otro_archivo")
@Data
@NoArgsConstructor
public class GastoOtroArchivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gasto_otro")
    private Long idGastoOtro;

    private String nombre;

    @JdbcTypeCode(SqlTypes.BINARY)
    private byte[] foto;

    private Date fec_creacion;
    private Date fec_actualizacion;
    private String user_creacion;
    private String user_actualizacion;

}
