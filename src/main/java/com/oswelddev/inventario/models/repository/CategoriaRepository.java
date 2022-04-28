package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {
}