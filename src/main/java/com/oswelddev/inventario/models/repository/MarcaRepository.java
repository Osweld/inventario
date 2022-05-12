package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Marca;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MarcaRepository extends PagingAndSortingRepository<Marca, Long> {

    List<Marca> findMarcasByNombreContaining(String nombre, Pageable pageable);
}