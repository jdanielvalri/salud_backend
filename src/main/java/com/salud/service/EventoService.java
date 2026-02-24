package com.salud.service;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Evento;
import com.salud.repository.CentroRepository;
import com.salud.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findByDesdeBetween (Date desde, Date hasta){
        return eventoRepository.findByDesdeBetween(desde,hasta);
    }

}
