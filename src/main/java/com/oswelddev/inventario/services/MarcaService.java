package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarcaService {

    Page<Marca> getAllMarcas(Pageable pageable);
    Marca getMarcaById(Long idMarca);
    Marca createMarca(Marca marca);
    Marca editMarca(Long idMarca,Marca marca);
    void deleteMarcaById(Long idMarca);
}
