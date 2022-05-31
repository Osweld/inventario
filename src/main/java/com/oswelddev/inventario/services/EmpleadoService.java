package com.oswelddev.inventario.services;

import com.oswelddev.inventario.models.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface EmpleadoService extends PersonaService{

    Page<Empleado> getAllEmpleados(Pageable pageable);

    Empleado getAllEmpleadosByPersona(Long idPersona);

    Empleado getEmpleadosByCodigoEmpleado(String codigoEmpleado);

    List<Empleado> searchEmpleado(String CodigoEmpleado);

    Empleado findEmpleadoByUsuario(String usuario);

    Empleado getEmpleadoById(Long idEmpleado);

    Boolean evaluateUsuarioAndPassword(String usuario, String password);

    Empleado createEmpleado(Empleado Empleado);

    Empleado editEmpleado(Long idEmpleado, Empleado Empleado);

    void deleteEmpleadoById(Long idEmpleado);

    public Boolean existById(Long idCliente);
}
