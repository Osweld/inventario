package com.oswelddev.inventario.models.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "marcas")
@Getter
@Setter
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca", nullable = false)
    private Long id;
    @NotBlank(message = "La marca no puede quedar vacia")
    @Size(max = 45, message = "La marca no puede exceder los 45 caracteres")
    @Column(nullable = false,unique = true)
    private String nombre;
    @Size(max = 1000, message = "La descripcion de la marca no puede exceder los 1000 caracteres")
    private String descripcion;

    public Marca(Long id) {
        this.id = id;
    }

    public Marca() {

    }

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
