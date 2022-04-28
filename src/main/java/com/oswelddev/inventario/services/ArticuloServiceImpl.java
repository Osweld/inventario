package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Articulo;
import com.oswelddev.inventario.models.entity.Categoria;
import com.oswelddev.inventario.models.entity.Marca;
import com.oswelddev.inventario.models.repository.ArticuloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService{

    private final ArticuloRepository articuloRepository;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulo> getAllArticulos(Pageable pageable) {
        return articuloRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulo> getAllArticulosByCategoria(Long idCategoria, Pageable pageable) {
        return articuloRepository.findAllByCategoria(new Categoria(idCategoria),pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulo> getAllArticulosByMarca(Long idMarca, Pageable pageable) {
        return articuloRepository.findAllByMarca(new Marca(idMarca),pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulo> getAllArticulosByMarcaAndCategoria(Long idMarca, Long idCategoria, Pageable pageable) {
        return articuloRepository.findAllByMarcaAndCategoria(new Marca(idMarca),new Categoria(idCategoria),pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Long idArticulo) {
        return articuloRepository.findById(idArticulo).orElseThrow();
    }

    @Override
    @Transactional
    public Articulo createArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    @Transactional
    public Articulo editArticulo(Long idArticulo, Articulo articulo) {
        return articuloRepository.findById(idArticulo).map(articuloDB ->{
            articuloDB.setNombre(articulo.getNombre());
            articuloDB.setDescripcion(articulo.getDescripcion());
            articuloDB.setCodigo(articulo.getCodigo());
            articuloDB.setStock(articulo.getStock());
            articuloDB.setPrecio(articulo.getPrecio());
            articuloDB.setCosto(articulo.getCosto());
            articuloDB.setActivo(articulo.getActivo());
            articuloDB.setCategoria(articulo.getCategoria());
            articuloDB.setMarca(articulo.getMarca());
            return articuloRepository.save(articuloDB);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteArticuloById(Long idArticulo) {
        articuloRepository.deleteById(idArticulo);
    }
}
