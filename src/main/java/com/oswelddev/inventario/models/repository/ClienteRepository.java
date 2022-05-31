package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Cliente;
import com.oswelddev.inventario.models.entity.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAllCliente( Pageable pageable);

    Cliente findByPersona(Long idPersona);

    Cliente findByIdCliente (Long idCliente);

    Cliente findByCodigoIdentidad(String codigoIdentidad);

    Boolean existsByCodigoIdentidad(String codigoIdentidad);

}