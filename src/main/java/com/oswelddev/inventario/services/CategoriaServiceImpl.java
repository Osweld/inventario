package com.oswelddev.inventario.services;

import com.oswelddev.inventario.exceptions.UniqueValidationException;
import com.oswelddev.inventario.models.entity.Categoria;
import com.oswelddev.inventario.models.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoriaById(Long idCategoria) {
        return categoriaRepository.findById(idCategoria).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> searchCategoria(String keywords) {
        return categoriaRepository.findCategoriaByNombreContainingIgnoreCase(keywords, PageRequest.of(0,5));
    }

    @Override
    @Transactional
    public Categoria createCategoria(Categoria categoria) {
        if(categoriaRepository.existsByNombre(categoria.getNombre())) throw new UniqueValidationException("Ya existe el nombre de la categoria");
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria editCategoria(Long idCategoria, Categoria categoria) {
         return categoriaRepository.findById(idCategoria).map( categoriaDB ->{
             if(!categoriaDB.getNombre().equals(categoria.getNombre())&&categoriaRepository.existsByNombre(categoria.getNombre())) throw new UniqueValidationException("Ya existe el nombre de la categoria");
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
