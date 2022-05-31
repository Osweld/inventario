package com.oswelddev.inventario.controllers;

import com.oswelddev.inventario.models.entity.Cliente;
import com.oswelddev.inventario.models.entity.Cliente;
import com.oswelddev.inventario.models.entity.Persona;
import com.oswelddev.inventario.services.ClienteService;
import com.oswelddev.inventario.services.ClienteService;
import com.oswelddev.inventario.services.PersonaService;
import com.oswelddev.inventario.utils.ControllerUtils;
import com.oswelddev.inventario.utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAllClientes(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "10", required = false) int size) {

        return new ResponseEntity<>(ControllerUtils.paginationUtils(
                clienteService.getAllClientes(PageRequest.of(page, size))), HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    ResponseEntity<Cliente> getCliente(@PathVariable Long idCliente) {

        if(idCliente != null && clienteService.existById(idCliente)) {
            return new ResponseEntity<>(clienteService.getClienteById(idCliente), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("Registro no encontrado"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchname/{keywords}")
    ResponseEntity<List<Cliente>> searchNombre(@PathVariable String keywords) {
       try {
           return new ResponseEntity(clienteService.searchPersonaByNombre(keywords), HttpStatus.OK);
       }catch (Error e){
           return new ResponseEntity(new Mensaje("Error: "+ e), HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/identidad/{noidentidad}")
    ResponseEntity<Cliente> findByNoIdentidad(@PathVariable String noIdentidad ) {
        try {
            Persona persona = clienteService.getPersonaByNoIdentidad(noIdentidad);
            return new ResponseEntity(clienteService.getClientesByPersona(persona.getId()), HttpStatus.OK);
        }catch (Error e){
            return new ResponseEntity(new Mensaje("Error: "+ e), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        if (cliente != null)
            return new ResponseEntity(clienteService.createCliente(cliente), HttpStatus.CREATED);
        return new ResponseEntity(new Mensaje("Registro vacio o invalido"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idCliente}")
    ResponseEntity<Cliente> editCliente(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente) {
        if (cliente != null)
            return new ResponseEntity(clienteService.editCliente(idCliente, cliente), HttpStatus.CREATED);
        return new ResponseEntity(new Mensaje("Registro vacio o invalido"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idCliente}")
    ResponseEntity<Map<String, Object>> deleteCliente(@PathVariable Long idCliente) {
        if (idCliente != null && clienteService.existById(idCliente)) {
            clienteService.deleteClienteById(idCliente);
            return new ResponseEntity(ControllerUtils.genericDeleteResponse(idCliente), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("Registro no encontrado"), HttpStatus.NOT_FOUND);
    }



}
