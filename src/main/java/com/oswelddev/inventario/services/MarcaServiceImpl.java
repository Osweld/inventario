package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Marca;
import com.oswelddev.inventario.models.repository.MarcaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService{

    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Marca> getAllMarcas(Pageable pageable) {
        return marcaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Marca getMarcaById(Long idMarca) {
        return marcaRepository.findById(idMarca).orElseThrow();//NoSuchElement...
    }

    @Override
    @Transactional(readOnly = true)
    public List<Marca> searchMarca(String nombre) {
        return marcaRepository.findMarcasByNombreContaining(nombre, PageRequest.of(0,5));
    }

    @Override
    @Transactional
    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    @Transactional
    public Marca editMarca(Long idMarca, Marca marca) {
        return marcaRepository.findById(idMarca).map( marcaDB -> {
            marcaDB.setNombre(marca.getNombre());
            marcaDB.setDescripcion(marca.getDescripcion());
            return marcaRepository.save(marcaDB);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteMarcaById(Long idMarca) {
        marcaRepository.deleteById(idMarca);
    }
}
