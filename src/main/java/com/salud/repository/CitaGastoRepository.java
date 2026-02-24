package com.salud.repository;

import com.salud.model.entity.Cita;
import com.salud.model.entity.CitaGasto;
import com.salud.model.projection.CitaGastoProjection;
import com.salud.model.projection.CitaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CitaGastoRepository extends JpaRepository<CitaGasto, Long> {

    void deleteByIdCita(Long idCita);

    @Query("select cg.id as id, cg.idCita as idCita, " +
            "cg.item as item, cg.idCentro as idCentro, " +
            "ce.nombre as nombreCentro, cg.fecha as fecha, cg.cantidad as cantidad, " +
            "cg.monto as monto, cg.flgFinanciado as flgFinanciado " +
            "from CitaGasto cg join Centro ce on cg.idCentro = ce.id " +
            "where cg.idCita = :idcita")
    List<CitaGastoProjection> findByCita(@Param("idcita") Long idCita);


}
