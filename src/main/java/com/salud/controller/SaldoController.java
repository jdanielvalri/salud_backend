package com.salud.controller;

import com.salud.model.entity.Parametro;
import com.salud.service.FinanciamientoService;
import com.salud.service.ParametroService;
import com.salud.service.SaldoService;
import com.salud.utilitario.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;

@RestController
@RequestMapping("/api/saldo")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @Autowired
    private ParametroService parametroService;

    @GetMapping("/getFechaEjecucionSaldo")
    public ResponseEntity<String> getFechaEjecucionSaldo() throws ParseException {
        String fechaCadena = "00/00/0000";
        Parametro parametro = parametroService.getByCodigo("FECEJESALD");

        if(parametro!=null){
            fechaCadena = DateUtil.convetirFechaACadena(parametro.getValorFecha(),"dd/MM/yyyy");
        }
        return ResponseEntity.ok(fechaCadena);
    }

    @GetMapping("/getSaldoActual")
    public ResponseEntity<BigDecimal> getSaldoActual() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(saldoService.getSaldoActual(user));
    }

    @GetMapping("/getSaldoInicialSistema")
    public ResponseEntity<BigDecimal> getSaldoInicialSistema() {
        return ResponseEntity.ok(saldoService.getSaldoInicialSistema());
    }

    @GetMapping("/getSaldoFinanciamiento")
    public ResponseEntity<BigDecimal> getSaldoFinanciamiento() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(saldoService.getSaldoFinanciamiento(user));
    }

    @GetMapping("/getGastoCitas")
    public ResponseEntity<BigDecimal> getGastoCitas() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(saldoService.getGastoCitas(user));
    }

    @GetMapping("/getGastoOtros")
    public ResponseEntity<BigDecimal> getGastoOtros() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(saldoService.getGastoOtros(user));
    }
}
