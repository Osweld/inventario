package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Articulo;
import com.oswelddev.inventario.models.entity.Categoria;
import com.oswelddev.inventario.models.entity.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticuloService {

    Page<Articulo> getAllArticulos(Pageable pageable);
    Page<Articulo> getAllArticulosByCategoria(Long idCategoria,Pageable pageable);
    Page<Articulo> getAllArticulosByMarca(Long idMarca,Pageable pageable);
    Page<Articulo> getAllArticulosByMarcaAndCategoria(Long idMarca,Long idCategoria,Pageable pageable);
    Articulo getArticulo(Long idArticulo);
    Articulo createArticulo(Articulo articulo);
    Articulo editArticulo(Long idArticulo,Articulo articulo);
    void deleteArticuloById(Long idArticulo);

}
