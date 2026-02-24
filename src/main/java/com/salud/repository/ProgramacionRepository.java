package com.salud.repository;

import com.salud.model.entity.Financiamiento;
import com.salud.model.entity.Programacion;
import com.salud.model.projection.FinanciamientoProjection;
import com.salud.model.projection.ProgramacionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramacionRepository extends JpaRepository<Programacion, Long> {

    @Query("SELECT p.id as id, " +
            "p.idPaciente as idPaciente, " +
            "p.fechaDesde as fechaDesde, " +
            "p.fechaHasta as fechaHasta, " +
            "pe.nombre || ' ' || pe.apePaterno || ' ' || pe.apeMaterno as nombrePaciente "  +
            "FROM Programacion p join Paciente pe " +
            "on p.idPaciente = pe.id " +
            "join UsuarioPaciente up on up.idPaciente = pe.id " +
            "join Usuario u on u.id = up.idUsuario " +
            "where u.usuario = :usuario and " +
            "(p.idPaciente = :idpaciente or :idpaciente is null)")
    List<ProgramacionProjection> findByFiltros(
            @Param("usuario") String usuario,
            @Param("idpaciente") Long idPaciente);

    @Query("SELECT p.id as id, " +
            "p.idPaciente as idPaciente, " +
            "p.fechaDesde as fechaDesde, " +
            "p.fechaHasta as fechaHasta, " +
            "pe.nombre || ' ' || pe.apePaterno || ' ' || pe.apeMaterno as nombrePaciente "  +
            "FROM Programacion p join Paciente pe " +
            "on p.idPaciente = pe.id " +
            "where " +
            "(p.id = :idprogramacion )")
    ProgramacionProjection getProgramacionAll(@Param("idprogramacion") Long idProgramacion);

}
