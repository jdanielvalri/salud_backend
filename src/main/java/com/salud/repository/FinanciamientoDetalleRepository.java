package com.salud.repository;

import com.salud.model.entity.Financiamiento;
import com.salud.model.entity.FinanciamientoDetalle;
import com.salud.model.projection.FinanciamientoDetalleProjection;
import com.salud.model.projection.FinanciamientoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanciamientoDetalleRepository extends JpaRepository<FinanciamientoDetalle, Long> {

    public FinanciamientoDetalle getByIdFinanciamientoAndIdAportante(Long idFinanciamiento, Long idAportante);

    @Query("SELECT f.id as idFinanciamiento, " +
            "fd.id as id, f.anio as anio, f.mes as mes, " +
            "fd.idAportante as idAportante, " +
            "fd.esperado as esperado, fd.entregado as entregado, " +
            "(fd.esperado - fd.entregado) as pendiente, " +
            "a.nombreCompleto as nombreAportante," +
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
            "FROM Financiamiento f join FinanciamientoDetalle fd " +
            "on f.id = fd.idFinanciamiento " +
            "join Aportante a on fd.idAportante = a.id " +
            "where " +
            "(fd.idFinanciamiento = :idfinanciamiento) and " +
            "(fd.idAportante = :idaportante or :idaportante is null)")
    List<FinanciamientoDetalleProjection> findByFiltros(@Param("idfinanciamiento") Long idFinanciamiento,
                                                        @Param("idaportante") Long idAportante);

    void deleteByIdFinanciamiento(Long idFinanciamiento);

}
