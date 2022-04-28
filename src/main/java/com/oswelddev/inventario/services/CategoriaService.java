package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {

    Page<Categoria> getAllCategorias(Pageable pageable);
    Categoria getCategoriaById(Long idCategoria);
    Categoria createCategoria(Categoria categoria);
    Categoria editCategoria(Long idCategoria, Categoria categoria);
    void deleteCategoriaById(Long idCategoria);
}
