package com.oswelddev.inventario.controllers;

import com.oswelddev.inventario.models.entity.Categoria;
import com.oswelddev.inventario.services.CategoriaService;
import com.oswelddev.inventario.utils.ControllerUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAllCategorias(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(ControllerUtils.paginationUtils(categoriaService.getAllCategorias(PageRequest.of(page, size))), HttpStatus.OK);
    }

    @GetMapping("/{idCategoria}")
    ResponseEntity<Categoria> getCategoriabyId(@PathVariable Long idCategoria) {
        return new ResponseEntity<>(categoriaService.getCategoriaById(idCategoria), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<Categoria>> getAllCategorias() {
        return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    ResponseEntity<List<Categoria>> searchCategoria(@PathVariable String keywords) {
        return new ResponseEntity<>(categoriaService.searchCategoria(keywords), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.createCategoria(categoria),HttpStatus.CREATED);
    }

    @PutMapping("/{idCategoria}")
    ResponseEntity<Categoria> editCategoria(@PathVariable Long idCategoria,@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.editCategoria(idCategoria,categoria),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCategoria}")
    ResponseEntity<Map<String,Object>> deleteCategoria(@PathVariable Long idCategoria){
        categoriaService.deleteCategoriaById(idCategoria);
        return new ResponseEntity<>(ControllerUtils.genericDeleteResponse(idCategoria),HttpStatus.OK);
    }



}
