package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    Boolean existsByNombre(String nombre);
}