package com.oswelddev.inventario.services;

import com.oswelddev.inventario.exceptions.UniqueValidationException;
import com.oswelddev.inventario.models.entity.Empleado;
import com.oswelddev.inventario.models.entity.Persona;
import com.oswelddev.inventario.models.repository.EmpleadoRepository;
import com.oswelddev.inventario.models.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService, PersonaService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private final EmpleadoRepository empleadoRepository;
    private final PersonaRepository personaRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, PersonaRepository personaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public Page<Empleado> getAllEmpleados(Pageable pageable) {
        return empleadoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado getAllEmpleadosByPersona(Long idPersona) {
        return empleadoRepository.findByPersona(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado getEmpleadosByCodigoEmpleado(String codigoEmpleado) {
        return empleadoRepository.findByCodigoEmpleado(codigoEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> searchEmpleado(String CodigoEmpleado) {
        return empleadoRepository.findEmpleadoByCodigoEmpleadoContainingIgnoreCase(CodigoEmpleado, PageRequest.of(0,5));
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findEmpleadoByUsuario(String usuario) {

        return empleadoRepository.findByUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado getEmpleadoById(Long idEmpleado) {
        return empleadoRepository.getById(idEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean evaluateUsuarioAndPassword(String usuario, String password){
        Empleado empleado = findEmpleadoByUsuario(usuario);
        // Descifrar
        return bCryptPasswordEncoder.matches (password, empleado.getPassword());
    }

    @Override
    @Transactional
    public Empleado createEmpleado(Empleado empleado) {
        if(empleadoRepository.existsByCodigoEmpleado(empleado.getCodigoEmpleado()))
            throw new UniqueValidationException("Ya existe el codigo de empleado");
        if(empleadoRepository.existsByUsuario(empleado.getUsuario()))
            throw new UniqueValidationException("Ya existe el usuario ingresado");
        // Cifrado
        empleado.setPassword(bCryptPasswordEncoder.encode(empleado.getPassword()));
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional
    public Empleado editEmpleado(Long idEmpleado, Empleado empleado) {

        return empleadoRepository.findById(idEmpleado).map(empleadoDB ->{
            if(!empleadoDB.getCodigoEmpleado().equals(empleado.getCodigoEmpleado())
                    && empleadoRepository.existsByCodigoEmpleado(empleado.getCodigoEmpleado()))
                throw new UniqueValidationException("Ya existe el codigo de empleado");
            if(!empleadoDB.getUsuario().equals(empleado.getUsuario())
                    &&  empleadoRepository.existsByUsuario(empleado.getUsuario()))
                throw new UniqueValidationException("Ya existe el usuario ingresado");
            empleadoDB.setPersona(empleado.getPersona());
            empleadoDB.setUsuario(empleado.getUsuario());
            empleadoDB.setPassword(empleado.getPassword());
            empleadoDB.setCodigoEmpleado(bCryptPasswordEncoder.encode(empleado.getPassword()));
            return empleadoRepository.save(empleadoDB);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteEmpleadoById(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existById(Long idEmpleado){
        boolean ret = false;
        if(empleadoRepository.getById(idEmpleado) != null) {
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
