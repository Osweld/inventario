package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {

    List<Categoria> findCategoriaByNombreContaining(String nombre, Pageable pageable);
}