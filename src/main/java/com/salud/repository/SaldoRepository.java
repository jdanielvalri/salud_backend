package com.salud.repository;

import com.salud.model.entity.Financiamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaldoRepository extends JpaRepository<Financiamiento, Long> {

    @Query(value = "SELECT get_saldo_actual(:usuario)", nativeQuery = true)
    BigDecimal getSaldoActual(@Param("usuario") String usuario);

    @Query(value = "SELECT get_saldo_inicial_sistema()", nativeQuery = true)
    BigDecimal getSaldoInicialSistema();

    @Query(value = "SELECT get_saldo_financiamiento(:usuario)", nativeQuery = true)
    BigDecimal getSaldoFinanciamiento(@Param("usuario") String usuario);

    @Query(value = "SELECT get_gasto_citas(:usuario)", nativeQuery = true)
    BigDecimal getGastoCitas(@Param("usuario") String usuario);

    @Query(value = "SELECT get_gasto_otros(:usuario)", nativeQuery = true)
    BigDecimal getGastoOtros(@Param("usuario") String usuario);

}
