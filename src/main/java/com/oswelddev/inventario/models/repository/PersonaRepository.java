package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findPersonasByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    List<Persona> findAllByNombreAndApellido(String nombre, String apellido);
    Persona findPersonaByNoIdentidad(String noIdentidad);
    Boolean existsByNoIdentidad(String noIdentidad);
}