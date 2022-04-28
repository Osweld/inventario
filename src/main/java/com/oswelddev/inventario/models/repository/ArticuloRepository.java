package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Articulo;
import com.oswelddev.inventario.models.entity.Categoria;
import com.oswelddev.inventario.models.entity.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    Page<Articulo> findAllByCategoria(Categoria categoria, Pageable pageable);
    Page<Articulo> findAllByMarca(Marca marca, Pageable pageable);
    Page<Articulo> findAllByMarcaAndCategoria(Marca marca,Categoria categoria, Pageable pageable);

}