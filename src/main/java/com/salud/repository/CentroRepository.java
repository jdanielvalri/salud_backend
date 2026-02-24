package com.salud.repository;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Paciente;
import com.salud.model.projection.PacienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Long> {

}
