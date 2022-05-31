package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface ClienteService extends PersonaService{

    Page<Cliente> getAllClientes(Pageable pageable);

    Cliente getClientesByPersona(Long idPersona);

    Cliente getClientesByCodigoIdentidad(String codigoIdentidad);

    Cliente getClienteById(Long idCliente);

    Cliente createCliente(Cliente Cliente);

    Cliente editCliente(Long idCliente, Cliente Cliente);

    void deleteClienteById(Long idCliente);


    public Boolean existById(Long idCliente);
}
