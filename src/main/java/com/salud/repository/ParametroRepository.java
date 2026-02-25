package com.salud.repository;

import com.salud.model.entity.Centro;
import com.salud.model.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

     Parametro getByCodigo(String codigo);

}
