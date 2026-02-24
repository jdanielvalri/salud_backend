package com.salud.repository;

import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.entity.FinanciamientoPaciente;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.projection.FinanciamientoPacienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanciamientoPacienteRepository extends JpaRepository<FinanciamientoPaciente, Long> {
    void deleteByIdFinanciamiento(Long idFinanciamiento);

    FinanciamientoPaciente getByIdFinanciamientoAndIdPaciente(Long idFinanciamiento, Long idPaciente);

    @Query("SELECT f.id as idFinanciamiento, " +
            "fp.id as id, fp.idPaciente as idPaciente, " +
            "p.nombre || ' ' || p.apePaterno || ' ' || p.apeMaterno as nombrePaciente " +
            "FROM Financiamiento f join FinanciamientoPaciente fp " +
            "on fp.idFinanciamiento = f.id join Paciente p " +
            "on p.id = fp.idPaciente "  +
            "where f.id = :idfinanciamiento ")
    List<FinanciamientoPacienteProjection> findByIdFinanciamiento(@Param("idfinanciamiento") Long idFinanciamiento);

}
