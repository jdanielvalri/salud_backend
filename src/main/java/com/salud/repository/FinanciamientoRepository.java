package com.salud.repository;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Financiamiento;
import com.salud.model.projection.FinanciamientoProjection;
import com.salud.model.projection.GastoOtroProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface FinanciamientoRepository extends JpaRepository<Financiamiento, Long> {

    @Query("SELECT f.id as id, " +
            "f.anio as anio, f.mes as mes, " +
            "case when f.mes = '01' then 'ENERO' " +
            "when f.mes = '02' then 'FEBRERO' " +
            "when f.mes = '03' then 'MARZO' " +
            "when f.mes = '04' then 'ABRIL' " +
            "when f.mes = '05' then 'MAYO' " +
            "when f.mes = '06' then 'JUNIO' " +
            "when f.mes = '07' then 'JULIO' " +
            "when f.mes = '08' then 'AGOSTO' " +
            "when f.mes = '09' then 'SEPTIEMBRE' " +
            "when f.mes = '10' then 'OCTUBRE' " +
            "when f.mes = '11' then 'NOVIEMBRE' " +
            "when f.mes = '12' then 'DICIEMBRE' else ' ' end as nombreMes " +
            "FROM Financiamiento f " +
            "where " +
            "(f.anio = :anio or :anio is null) and " +
            "(f.mes = :mes or :mes = '00') " +
            "order by f.anio desc, f.mes desc ")
    List<FinanciamientoProjection> findByFiltros(@Param("anio") Integer anio,
                                                 @Param("mes") String mes);

    @Query("SELECT f.id as id, " +
            "f.anio as anio, f.mes as mes, " +
            "case when f.mes = '01' then 'ENERO' " +
            "when f.mes = '02' then 'FEBRERO' " +
            "when f.mes = '03' then 'MARZO' " +
            "when f.mes = '04' then 'ABRIL' " +
            "when f.mes = '05' then 'MAYO' " +
            "when f.mes = '06' then 'JUNIO' " +
            "when f.mes = '07' then 'JULIO' " +
            "when f.mes = '08' then 'AGOSTO' " +
            "when f.mes = '09' then 'SEPTIEMBRE' " +
            "when f.mes = '10' then 'OCTUBRE' " +
            "when f.mes = '11' then 'NOVIEMBRE' " +
            "when f.mes = '12' then 'DICIEMBRE' else ' ' end as nombreMes " +
            "FROM Financiamiento f " +
            "where " +
            "f.id = :idfinanciamiento")
    FinanciamientoProjection getFinanciamientoAll(@Param("idfinanciamiento") Long idFinanciamiento);

    public Financiamiento getByAnioAndMes(Integer anio, String mes);

}
