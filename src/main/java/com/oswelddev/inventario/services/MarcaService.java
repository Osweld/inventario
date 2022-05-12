package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MarcaService {

    Page<Marca> getAllMarcas(Pageable pageable);
    Marca getMarcaById(Long idMarca);
    List<Marca> searchMarca(String nombre);
    Marca createMarca(Marca marca);
    Marca editMarca(Long idMarca,Marca marca);
    void deleteMarcaById(Long idMarca);
}
