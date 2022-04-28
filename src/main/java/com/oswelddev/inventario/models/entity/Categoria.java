package com.oswelddev.inventario.models.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "categorias")
@Getter
@Setter
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Long id;
    @NotBlank(message = "La categoria no puede quedar vacia")
    @Size(max = 45, message = "La categoria no puede exceder los 45 caracteres")
    @Column(nullable = false,unique = true)
    private String nombre;
    @Size(max = 1000, message = "La descripcion de la categoria no puede exceder los 1000 caracteres")
    private String descripcion;

    public Categoria(Long id) {
        this.id = id;
    }

    public Categoria() {

    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}







