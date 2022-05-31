package com.oswelddev.inventario.services;

import com.oswelddev.inventario.exceptions.UniqueValidationException;
import com.oswelddev.inventario.models.entity.Cliente;
import com.oswelddev.inventario.models.entity.Persona;
import com.oswelddev.inventario.models.repository.ClienteRepository;
import com.oswelddev.inventario.models.repository.PersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ClienteServiceImpl implements ClienteService, PersonaService{

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> getAllClientes(Pageable pageable) {

        return clienteRepository.findAllCliente(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getClientesByPersona(Long idPersona) {

        return clienteRepository.findByPersona(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getClientesByCodigoIdentidad(String codigoIdentidad) {
        return clienteRepository.findByCodigoIdentidad(codigoIdentidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getClienteById(Long idCliente) {

        return clienteRepository.findByIdCliente(idCliente);
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        if(clienteRepository.existsByCodigoIdentidad(cliente.getCodigoIdentidad())) 
            throw new UniqueValidationException("Ya existe el codigo de identidad del cliente");
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente editCliente(Long idCliente, Cliente cliente) {
        return clienteRepository.findById(idCliente).map(clienteDB -> {
            if (!clienteDB.getCodigoIdentidad().equals(cliente.getCodigoIdentidad()) 
                    && clienteRepository.existsByCodigoIdentidad(cliente.getCodigoIdentidad()))
                throw new UniqueValidationException("Ya existe el codigo del cliente");
            clienteDB.setCodigoIdentidad(cliente.getCodigoIdentidad());
            clienteDB.setPersona(cliente.getPersona());
            return clienteRepository.save(clienteDB);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteClienteById(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existById(Long idCliente){
        boolean ret = false;
        if(clienteRepository.findByIdCliente(idCliente) != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> getAllPersonas(Pageable pageable) {
        return personaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaById(Long idPersona) {
        return personaRepository.getById(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> searchPersonaByNombre(String nombre) {
        return personaRepository.findPersonasByNombreContainingIgnoreCase(nombre, PageRequest.of(0,5));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findPersonaByNombreAndApellido(String nombre, String apellido) {
        return personaRepository.findAllByNombreAndApellido(nombre, apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaByNoIdentidad(String noIdentidad) {
        return personaRepository.findPersonaByNoIdentidad(noIdentidad);
    }

    @Override
    @Transactional
    public Persona createPersona(Persona persona) {
        if(personaRepository.existsByNoIdentidad(persona.getNoIdentidad()))
            throw new UniqueValidationException("Ya existe la persona con el número de identificación ingresado");
        return personaRepository.save(persona);
    }

    @Override
    @Transactional
    public Persona editPersona(Long idPersona, Persona persona) {
        return personaRepository.findById(idPersona).map( personaDB -> {
            if(!personaDB.getNoIdentidad().equals(persona.getNoIdentidad())
                    && personaRepository.existsByNoIdentidad(persona.getNoIdentidad()))
                throw new UniqueValidationException("Ya existe la persona con el número de identificación ingresado");
            personaDB.setNombre(persona.getNombre());
            personaDB.setApellidos(persona.getApellidos());
            personaDB.setNoIdentidad(persona.getNoIdentidad());
            personaDB.setFechaNacimiento(persona.getFechaNacimiento());
            personaDB.setTelefono(persona.getTelefono());
            personaDB.setActivo(persona.getActivo());
            return personaRepository.save(personaDB);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void deletePersonaById(Long idPersona) {
        personaRepository.deleteById(idPersona);
    }
}
