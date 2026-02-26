package com.salud.repository;

import com.salud.model.entity.Cita;
import com.salud.model.entity.Paciente;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.GastoOtroProjection;
import com.salud.model.projection.PacienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("select c.id as idCita, c.idPaciente as idPaciente, c.idCentro as idCentro, " +
            "c.idMedico as idMedico, c.idEspecialidad as idEspecialidad, " +
            "pe.nombre || ' ' || pe.apePaterno || ' ' || pe.apeMaterno as nombrePaciente, " +
            "e.nombre as nombreEspecialidad, m.nombre as nombreMedico,  " +
            "ce.nombre as nombreCentro, c.fecha as fecha, to_char(c.fecha,'dd/MM/yyyy') as fechaCadena, " +
            "c.hora as hora, c.estado as estado, c.comentario as comentario, " +
            "case when c.estado = 'PEND' then 'Pendiente' when c.estado = 'COMP' then 'Completado' " +
            "else '' end as nombreEstado, " +
            "COALESCE((select sum(cg.monto) from CitaGasto cg where cg.idCita = c.id),0.0) as montoTotal " +
            "from Cita c join Paciente pe on c.idPaciente = pe.id " +
            "join UsuarioPaciente up on up.idPaciente = pe.id " +
            "join Usuario u on u.id = up.idUsuario " +
            "join Especialidad e on c.idEspecialidad = e.id " +
            "join Medico m on c.idMedico = m.id " +
            "join Centro ce on c.idCentro = ce.id " +
            "where u.usuario = :usuario and " +
            "(to_char(c.fecha,'YYYYMMDD') >= to_char(cast(:desde as date),'YYYYMMDD') and " +
            "to_char(c.fecha,'YYYYMMDD') <= to_char(cast(:hasta as date),'YYYYMMDD')) and " +
            "(c.idPaciente = :idpaciente or :idpaciente is null) and " +
            "(c.idEspecialidad = :idespecialidad or :idespecialidad is null) and " +
            "(c.idMedico = :idmedico or :idmedico is null) and " +
            "(c.estado = :estado or :estado is null) " +
            "order by c.fecha desc")
    List<CitaProjection> findByFiltros(@Param("usuario") String usuario,
                                       @Param("desde") Date desde,
                                       @Param("hasta") Date hasta,
                                       @Param("idpaciente") Long idPaciente,
                                       @Param("idespecialidad") Long idEspecialidad,
                                       @Param("idmedico") Long idMedico,
                                       @Param("estado") String estado);

    @Query("select c.id as id, c.idPaciente as idPaciente, c.idCentro as idCentro, " +
            "c.idEspecialidad as idEspecialidad, " +
            "pe.nombre || ' ' || pe.apePaterno || ' ' || pe.apeMaterno as nombrePaciente, " +
            "e.nombre as nombreEspecialidad, " +
            "ce.nombre as nombreCentro, c.fecha as fecha, " +
            "to_char(c.fecha,'dd/MM/yyyy') as fechaCadena, " +
            "c.comentario as descripcion, 'C' as tipo, " +
            "COALESCE((select sum(cg.monto) from CitaGasto cg where cg.idCita = c.id),0.0) as montoTotal, " +
            "COALESCE((select sum(cg.monto) from CitaGasto cg where cg.idCita = c.id and cg.flgFinanciado = 'S'),0.0) as montoFinanciado  " +
            "from Cita c join Paciente pe on c.idPaciente = pe.id " +
            "join UsuarioPaciente up on up.idPaciente = pe.id " +
            "join Usuario u on u.id = up.idUsuario " +
            "join Especialidad e on c.idEspecialidad = e.id " +
            "join Medico m on c.idMedico = m.id " +
            "join Centro ce on c.idCentro = ce.id " +
            "where u.usuario = :usuario and " +
            "(to_char(c.fecha,'YYYYMMDD') >= to_char(cast(:desde as date),'YYYYMMDD') and " +
            "to_char(c.fecha,'YYYYMMDD') <= to_char(cast(:hasta as date),'YYYYMMDD')) and " +
            "(c.idPaciente = :idpaciente or :idpaciente is null) and " +
            "(c.idEspecialidad = :idespecialidad or :idespecialidad is null) " +
            "order by c.fecha desc")
    List<GastoOtroProjection> findByFiltrosGastoOtro(@Param("usuario") String usuario,
                                                           @Param("desde") Date desde,
                                                           @Param("hasta") Date hasta,
                                                           @Param("idpaciente") Long idPaciente,
                                                           @Param("idespecialidad") Long idEspecialidad);


    @Query("select c.id as idCita, c.idPaciente as idPaciente, c.idCentro as idCentro, " +
            "c.idMedico as idMedico, c.idEspecialidad as idEspecialidad, " +
            "pe.nombre || ' ' || pe.apePaterno || ' ' || pe.apeMaterno as nombrePaciente, " +
            "e.nombre as nombreEspecialidad, m.nombre as nombreMedico,  " +
            "ce.nombre as nombreCentro, c.fecha as fecha, c.hora as hora, " +
            "to_char(c.fecha,'dd/MM/yyyy') as fechaCadena, " +
            "c.estado as estado, c.comentario as comentario, " +
            "case when c.estado = 'PEND' then 'Pendiente' when c.estado = 'COMP' then 'Completado' " +
            "else '' end as nombreEstado, " +
            "COALESCE((select sum(cg.monto) from CitaGasto cg where cg.idCita = c.id),0.0) as montoTotal " +
            "from Cita c join Paciente pe on c.idPaciente = pe.id " +
            "join Especialidad e on c.idEspecialidad = e.id " +
            "join Medico m on c.idMedico = m.id " +
            "join Centro ce on c.idCentro = ce.id " +
            "where c.id = :idcita")
    CitaProjection getCitaAll(@Param("idcita") Long idCita);
}
