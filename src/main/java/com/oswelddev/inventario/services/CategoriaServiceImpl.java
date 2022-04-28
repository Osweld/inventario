package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Categoria;
import com.oswelddev.inventario.models.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Categoria> getAllCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoriaById(Long idCategoria) {
        return categoriaRepository.findById(idCategoria).orElseThrow();
    }

    @Override
    @Transactional
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria editCategoria(Long idCategoria, Categoria categoria) {
         return categoriaRepository.findById(idCategoria).map( categoriaDB ->{
             categoriaDB.setNombre(categoria.getNombre());
             categoriaDB.setDescripcion(categoria.getDescripcion());
             return categoriaRepository.save(categoriaDB);
         }).orElseThrow();

    }

    @Override
    @Transactional
    public void deleteCategoriaById(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}
