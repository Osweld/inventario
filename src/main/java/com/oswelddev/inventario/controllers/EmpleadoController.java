package com.oswelddev.inventario.controllers;

import com.oswelddev.inventario.models.entity.Empleado;
import com.oswelddev.inventario.models.entity.Persona;
import com.oswelddev.inventario.services.EmpleadoService;
import com.oswelddev.inventario.utils.ControllerUtils;
import com.oswelddev.inventario.utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/empleado")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAllEmpleados(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "10", required = false) int size) {

        return new ResponseEntity<>(ControllerUtils.paginationUtils(
                empleadoService.getAllEmpleados(PageRequest.of(page, size))), HttpStatus.OK);
    }

    @GetMapping("/{idEmpleado}")
    ResponseEntity<Empleado> getEmpleado(@PathVariable Long idEmpleado) {

        if(idEmpleado != null && empleadoService.existById(idEmpleado)) {
            return new ResponseEntity<>(empleadoService.getEmpleadoById(idEmpleado), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("Registro no encontrado"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchname/{keywords}")
    ResponseEntity<List<Empleado>> searchNombre(@PathVariable String keywords) {
        try {
            return new ResponseEntity(empleadoService.searchPersonaByNombre(keywords), HttpStatus.OK);
        }catch (Error e){
            return new ResponseEntity(new Mensaje("Error: "+ e), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/identidad/{noidentidad}")
    ResponseEntity<Empleado> findByNoIdentidad(@PathVariable String noIdentidad ) {
        try {
            Persona persona = empleadoService.getPersonaByNoIdentidad(noIdentidad);
            return new ResponseEntity(empleadoService.getAllEmpleadosByPersona(persona.getId()) , HttpStatus.OK);
        }catch (Error e){
            return new ResponseEntity(new Mensaje("Error: "+ e), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Empleado> createEmpleado(@Valid @RequestBody Empleado Empleado) {
        if (Empleado != null)
            return new ResponseEntity(empleadoService.createEmpleado(Empleado), HttpStatus.CREATED);
        return new ResponseEntity(new Mensaje("Registro vacio o invalido"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idEmpleado}")
    ResponseEntity<Empleado> editEmpleado(@PathVariable Long idEmpleado, @Valid @RequestBody Empleado Empleado) {
        if (Empleado != null)
            return new ResponseEntity(empleadoService.editEmpleado(idEmpleado, Empleado), HttpStatus.CREATED);
        return new ResponseEntity(new Mensaje("Registro vacio o invalido"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idEmpleado}")
    ResponseEntity<Map<String, Object>> deleteEmpleado(@PathVariable Long idEmpleado) {
        if (idEmpleado != null && empleadoService.existById(idEmpleado)) {
            empleadoService.deleteEmpleadoById(idEmpleado);
            return new ResponseEntity(ControllerUtils.genericDeleteResponse(idEmpleado), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("Registro no encontrado"), HttpStatus.NOT_FOUND);
    }
}
