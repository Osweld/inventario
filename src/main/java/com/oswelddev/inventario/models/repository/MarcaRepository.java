package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Marca;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findMarcasByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Boolean existsByNombre(String nombre);
}