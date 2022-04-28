package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Marca;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarcaRepository extends PagingAndSortingRepository<Marca, Long> {

}