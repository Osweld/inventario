package com.oswelddev.inventario.services;

import com.oswelddev.inventario.exceptions.UniqueValidationException;
import com.oswelddev.inventario.models.entity.Persona;
import com.oswelddev.inventario.models.repository.PersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> getAllPersonas(Pageable pageable) {
        return personaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaById(Long idPersona) {
        return personaRepository.getById(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> searchPersonaByNombre(String nombre) {
        return personaRepository.findPersonasByNombreContainingIgnoreCase(nombre, PageRequest.of(0,5));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findPersonaByNombreAndApellido(String nombre, String apellido) {
        return personaRepository.findAllByNombreAndApellido(nombre, apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaByNoIdentidad(String noIdentidad) {
        return personaRepository.findPersonaByNoIdentidad(noIdentidad);
    }

    @Override
    @Transactional
    public Persona createPersona(Persona persona) {
        if(personaRepository.existsByNoIdentidad(persona.getNoIdentidad()))
            throw new UniqueValidationException("Ya existe la persona con el número de identificación ingresado");
        return personaRepository.save(persona);
    }

    @Override
    @Transactional
    public Persona editPersona(Long idPersona, Persona persona) {
        return personaRepository.findById(idPersona).map( personaDB -> {
            if(!personaDB.getNoIdentidad().equals(persona.getNoIdentidad())
                    && personaRepository.existsByNoIdentidad(persona.getNoIdentidad()))
                throw new UniqueValidationException("Ya existe la persona con el número de identificación ingresado");
            personaDB.setNombre(persona.getNombre());
            personaDB.setApellidos(persona.getApellidos());
            personaDB.setNoIdentidad(persona.getNoIdentidad());
            personaDB.setFechaNacimiento(persona.getFechaNacimiento());
            personaDB.setTelefono(persona.getTelefono());
            personaDB.setActivo(persona.getActivo());
            return personaRepository.save(personaDB);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void deletePersonaById(Long idPersona) {
        personaRepository.deleteById(idPersona);
    }
}
