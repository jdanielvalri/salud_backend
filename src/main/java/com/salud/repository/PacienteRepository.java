package com.salud.repository;

import com.salud.model.entity.Paciente;
import com.salud.model.entity.Usuario;
import com.salud.model.projection.PacienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select pe.id as idPaciente, up.idUsuario as idUsuario, " +
            "pe.nombre as nombre, pe.apePaterno as apePaterno, pe.apeMaterno as apeMaterno, " +
            "pe.documento as documento, pe.sexo as sexo, pe.fecNacimiento as fecNacimiento " +
            "from Paciente pe join UsuarioPaciente up on up.idPaciente = pe.id " +
            "join Usuario u on u.id = up.idUsuario " +
            "where u.usuario = :usuario")
    List<PacienteProjection> findByUsuario(@Param("usuario") String usuario);
}
