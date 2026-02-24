package com.salud.repository;

import com.salud.model.entity.CitaArchivo;
import com.salud.model.entity.CitaGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaArchivoRepository extends JpaRepository<CitaArchivo, Long> {

    void deleteByIdCita(Long idCita);

}
