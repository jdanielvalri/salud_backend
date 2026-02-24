package com.salud.repository;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByDesdeBetween (Date desde, Date hasta);
}
