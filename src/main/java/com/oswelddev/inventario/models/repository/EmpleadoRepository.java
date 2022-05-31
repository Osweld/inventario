package com.oswelddev.inventario.models.repository;

import com.oswelddev.inventario.models.entity.Empleado;
import com.oswelddev.inventario.models.entity.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findEmpleadoByCodigoEmpleadoContainingIgnoreCase(String codigoEmpleado, Pageable pageable);

    Empleado findByPersona(Long idPersona);

    Empleado findByCodigoEmpleado(String codigoEmpleado);

    Empleado findByUsuario(String usuario);

    Boolean existsByCodigoEmpleado(String codigoEmpleado);

    Boolean existsByUsuario(String usuario);
}