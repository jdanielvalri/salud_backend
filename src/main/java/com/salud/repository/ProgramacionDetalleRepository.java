package com.salud.repository;

import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.entity.ProgramacionDetalle;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramacionDetalleRepository extends JpaRepository<ProgramacionDetalle, Long> {

    List<ProgramacionDetalle> getByIdProgramacionOrderByOrdenAsc(Long idProgramacion);
}
