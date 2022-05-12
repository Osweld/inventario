package com.oswelddev.inventario.controllers;

import com.oswelddev.inventario.models.entity.Marca;
import com.oswelddev.inventario.services.MarcaService;
import com.oswelddev.inventario.utils.ControllerUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/marcas")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("")
    ResponseEntity<Map<String,Object>> getAllMarcas(@RequestParam( defaultValue = "0", required = false) int page, @RequestParam( defaultValue = "10", required = false) int size){
        return new ResponseEntity<>(ControllerUtils.paginationUtils(marcaService.getAllMarcas(PageRequest.of(page, size))), HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    ResponseEntity<List<Marca>> getAllMarcas(@PathVariable String keywords){
        return new ResponseEntity<>(marcaService.searchMarca(keywords), HttpStatus.OK);
    }

    @GetMapping("/{idMarca}")
    ResponseEntity<Marca> getMarcaById(@PathVariable Long idMarca){
        return new ResponseEntity<>(marcaService.getMarcaById(idMarca),HttpStatus.OK);
    }
    @PostMapping("")
    ResponseEntity<Marca> createMarca(@Valid @RequestBody Marca marca){
        return new ResponseEntity<>(marcaService.createMarca(marca),HttpStatus.CREATED);
    }

    @PutMapping("/{idMarca}")
    ResponseEntity<Marca> editMarca(@PathVariable Long idMarca,@Valid @RequestBody Marca marca){
        return new ResponseEntity<>(marcaService.editMarca(idMarca,marca),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idMarca}")
    ResponseEntity<Map<String,Object>> deleteMarca(@PathVariable Long idMarca){
        marcaService.deleteMarcaById(idMarca);
        return new ResponseEntity<>(ControllerUtils.genericDeleteResponse(idMarca),HttpStatus.OK);
    }
}
