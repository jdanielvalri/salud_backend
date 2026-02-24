package com.salud.repository;

import com.salud.model.entity.Aportante;
import com.salud.model.entity.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AportanteRepository extends JpaRepository<Aportante, Long> {

}
