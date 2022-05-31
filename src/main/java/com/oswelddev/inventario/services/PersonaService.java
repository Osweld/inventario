package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface PersonaService {

    Page<Persona> getAllPersonas(Pageable pageable);

    List<Persona> getAllPersonas();

    Persona getPersonaById(Long idPersona);

    List<Persona> searchPersonaByNombre(String nombre);

    List<Persona> findPersonaByNombreAndApellido(String nombre, String apellido);

    Persona getPersonaByNoIdentidad(String noIdentidad);

    Persona createPersona(Persona Persona);

    Persona editPersona(Long idPersona, Persona Persona);

    void deletePersonaById(Long idPersona);
}
