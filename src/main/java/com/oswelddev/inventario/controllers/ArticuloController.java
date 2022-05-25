package com.oswelddev.inventario.controllers;

import com.oswelddev.inventario.models.entity.Articulo;
import com.oswelddev.inventario.services.ArticuloService;
import com.oswelddev.inventario.utils.ControllerUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/articulos")
@CrossOrigin(origins = "http://localhost:4200")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAllArticulos(@RequestParam(defaultValue = "0", required = false) int page,
                                                        @RequestParam(defaultValue = "10", required = false) int size,
                                                        @RequestParam(defaultValue = "0", required = false) long marca,
                                                        @RequestParam(defaultValue = "0", required = false) long categoria) {
//Ver si se puede mejorar este codigo
        if (marca > 0 && categoria > 0) {
            return new ResponseEntity<>(ControllerUtils.paginationUtils(
                    articuloService.getAllArticulosByMarcaAndCategoria(marca, categoria, PageRequest.of(page, size)))
                    , HttpStatus.OK);
        } else if (marca > 0) {
            return new ResponseEntity<>(ControllerUtils.paginationUtils(
                    articuloService.getAllArticulosByMarca(marca, PageRequest.of(page, size)))
                    , HttpStatus.OK);
        } else if (categoria > 0) {
            return new ResponseEntity<>(ControllerUtils.paginationUtils(
                    articuloService.getAllArticulosByCategoria(categoria, PageRequest.of(page, size)))
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ControllerUtils.paginationUtils(
                    articuloService.getAllArticulos(PageRequest.of(page, size)))
                    , HttpStatus.OK);
        }
    }

    @GetMapping("/{idArticulo}")
    ResponseEntity<Articulo> getArticulo(@PathVariable Long idArticulo) {
        return new ResponseEntity<>(articuloService.getArticulo(idArticulo), HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    ResponseEntity<List<Articulo>> searchArticulo(@PathVariable String keywords) {
        return new ResponseEntity<>(articuloService.searchArticulo(keywords), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Articulo> createArticulo(@Valid @RequestBody Articulo articulo) {
        return new ResponseEntity<>(articuloService.createArticulo(articulo), HttpStatus.CREATED);
    }

    @PutMapping("/{idArticulo}")
    ResponseEntity<Articulo> editArticulo(@PathVariable Long idArticulo, @Valid @RequestBody Articulo articulo) {
        return new ResponseEntity<>(articuloService.editArticulo(idArticulo, articulo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idArticulo}")
    ResponseEntity<Map<String, Object>> deleteArticulo(@PathVariable Long idArticulo) {
        articuloService.deleteArticuloById(idArticulo);
        return new ResponseEntity<>(ControllerUtils.genericDeleteResponse(idArticulo), HttpStatus.OK);
    }

}
