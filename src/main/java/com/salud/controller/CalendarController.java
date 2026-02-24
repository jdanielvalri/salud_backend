package com.salud.controller;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Cita;
import com.salud.model.entity.Evento;
import com.salud.model.projection.CitaProjection;
import com.salud.model.response.CalendarResponse;
import com.salud.service.CentroService;
import com.salud.service.CitaService;
import com.salud.service.EventoService;
import com.salud.utilitario.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private EventoService eventoService;

    @GetMapping("/listarEventos")
    public List<CalendarResponse> getAll( @RequestParam String start,
                                          @RequestParam String end) throws ParseException {
        System.out.println("start " + start);
        System.out.println("end " + end);
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        List<CalendarResponse> calendars = new ArrayList<>();

        List<CitaProjection> citas= citaService.findByFiltros(user, DateUtil.convetirCadenaAFecha(start),
                DateUtil.convetirCadenaAFecha(end),null,null,null,null);

        for (CitaProjection cita : citas) {
            CalendarResponse evento = new CalendarResponse();
            evento.setId(cita.getIdCita().toString());
            evento.setTitle("Cita: " + cita.getNombrePaciente() + " (" + cita.getHora().format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
            evento.setStart(DateUtil.convetirFechaACadena(cita.getFecha(), "YYYY-MM-dd"));
            evento.setEnd(DateUtil.convetirFechaACadena(cita.getFecha(), "YYYY-MM-dd"));
            evento.setEspecialidad(cita.getNombreEspecialidad());
            evento.setCentro(cita.getNombreCentro());
            evento.setDoctor(cita.getNombreMedico());
            evento.setEstado(cita.getNombreEstado());
            evento.setTipo("C");
            calendars.add(evento);
        };

        List<Evento> eventos = eventoService.findByDesdeBetween(
                DateUtil.convetirCadenaAFecha(start),
                DateUtil.convetirCadenaAFecha(end)
        );

        for(Evento evento : eventos){
            CalendarResponse evenAcom = new CalendarResponse();
            evenAcom.setId(evento.getId().toString());
            evenAcom.setTitle(evento.getTitulo());
            evenAcom.setStart(DateUtil.convetirFechaACadena(evento.getDesde(), "YYYY-MM-dd"));
            evenAcom.setEnd(DateUtil.convetirFechaACadena(evento.getHasta(), "YYYY-MM-dd"));
            evenAcom.setBackgroundColor("green");
            evenAcom.setTipo(evento.getTipo());
            calendars.add(evenAcom);
        }

        return calendars;
    }
}
