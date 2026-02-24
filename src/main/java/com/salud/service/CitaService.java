package com.salud.service;

import com.salud.model.entity.Cita;
import com.salud.model.entity.Paciente;
import com.salud.model.projection.CitaProjection;
import com.salud.model.projection.PacienteProjection;
import com.salud.repository.CitaArchivoRepository;
import com.salud.repository.CitaGastoRepository;
import com.salud.repository.CitaRepository;
import com.salud.repository.PacienteRepository;
import com.salud.utilitario.StringValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private CitaGastoRepository citaGastoRepository;

    @Autowired
    private CitaGastoService citaGastoService;

    @Autowired
    private CitaArchivoRepository citaArchivoRepository;

    public List<Cita> getAll(){
        return citaRepository.findAll();
    }

    public Cita getById(Long id){

        Cita cita = citaRepository.findById(id).orElse(null);

        if(cita!=null){
            cita.setMontoTotal(citaGastoService.getMontoByCita(id));
        }

        return cita;
    }

    public CitaProjection getCitaAll(Long id){
        return citaRepository.getCitaAll(id);
    }

    @Transactional
    public Cita create(Cita cita){
        return citaRepository.save(cita);
    }

    @Transactional
    public Cita update(Long idCita, Cita actualizado ){
        Cita cita = getById(idCita);
        if (cita != null) {
            cita.setIdPaciente(actualizado.getIdPaciente());
            cita.setIdCentro(actualizado.getIdCentro());
            cita.setIdMedico(actualizado.getIdMedico());
            cita.setIdEspecialidad(actualizado.getIdEspecialidad());
            cita.setFecha(actualizado.getFecha());
            cita.setHora(actualizado.getHora());
            cita.setEstado(actualizado.getEstado());
            cita.setComentario(actualizado.getComentario());
            cita.setUser_actualizacion(actualizado.getUser_actualizacion());
            cita.setFec_actualizacion(new Date());

            return citaRepository.save(cita);
        }

        return null;
    }

    @Transactional
    public void delete(Long idCita){
        citaGastoRepository.deleteByIdCita(idCita);
        citaArchivoRepository.deleteByIdCita(idCita);
        citaRepository.deleteById(idCita);
    }

    public List<CitaProjection> findByFiltros(String usuario, Date desde, Date hasta,
                                              Long idPaciente, Long idEspecialidad, Long idMedico,
                                              String estado){
        return citaRepository.findByFiltros(usuario,desde,hasta,idPaciente,idEspecialidad,idMedico,estado);
    }

}
