package com.salud.repository;

import com.salud.model.entity.GastoOtro;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.GastoOtroProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GastoOtroRepository extends JpaRepository<GastoOtro, Long> {

    @Query("select go.id as id, go.idPaciente as idPaciente, go.idCentro as idCentro, " +
            "go.idEspecialidad as idEspecialidad, " +
            "pe.nombre || ' ' || pe.apePaterno || ' ' || pe.apeMaterno as nombrePaciente, " +
            "e.nombre as nombreEspecialidad, " +
            "ce.nombre as nombreCentro, go.fecha as fecha, " +
            "to_char(go.fecha,'dd/MM/yyyy') as fechaCadena, " +
            "go.monto as montoTotal, " +
            "case when go.flgFinanciado='S' then go.monto else 0.0 end as montoFinanciado, " +
            "go.descripcion as descripcion, 'O' as tipo " +
            "from GastoOtro go join Paciente pe on go.idPaciente = pe.id " +
            "join UsuarioPaciente up on up.idPaciente = pe.id " +
            "join Usuario u on u.id = up.idUsuario " +
            "join Especialidad e on go.idEspecialidad = e.id " +
            "join Centro ce on go.idCentro = ce.id " +
            "where u.usuario = :usuario and " +
            "(to_char(go.fecha,'YYYYMMDD') >= to_char(cast(:desde as date),'YYYYMMDD') and " +
            "to_char(go.fecha,'YYYYMMDD') <= to_char(cast(:hasta as date),'YYYYMMDD')) and " +
            "(go.idPaciente = :idpaciente or :idpaciente is null) and " +
            "(go.idEspecialidad = :idespecialidad or :idespecialidad is null)")
    List<GastoOtroProjection> findByFiltros(@Param("usuario") String usuario,
                                            @Param("desde") Date desde,
                                            @Param("hasta") Date hasta,
                                            @Param("idpaciente") Long idPaciente,
                                            @Param("idespecialidad") Long idEspecialidad);


}
