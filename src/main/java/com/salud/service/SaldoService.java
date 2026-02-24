package com.salud.service;

import com.salud.repository.FinanciamientoRepository;
import com.salud.repository.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository saldoRepository;

    public BigDecimal getSaldoActual(String usuario){
        return saldoRepository.getSaldoActual(usuario);
    }

    public BigDecimal getSaldoInicialSistema(){
        return saldoRepository.getSaldoInicialSistema();
    }

    public BigDecimal getSaldoFinanciamiento(String usuario){
        return saldoRepository.getSaldoFinanciamiento(usuario);
    }

    public BigDecimal getGastoCitas(String usuario){
        return saldoRepository.getGastoCitas(usuario);
    }

    public BigDecimal getGastoOtros(String usuario){
        return saldoRepository.getGastoOtros(usuario);
    }
}
