package com.oswelddev.inventario.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "articulos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo", nullable = false)
    private Long id;

    @NotBlank(message = "El articulo no puede quedar vacio")
    @Size(max = 45, message = "El articulo no puede exceder los 45 caracteres")
    @Column(name = "nombre", length = 45,nullable = false,unique = true)
    private String nombre;

    @Size(max = 1000, message = "La descripcion de el articulo no puede exceder los 1000 caracteres")
    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @NotNull(message = "El stock del articulo no puede quedar vacio")
    @Column(name = "stock" , nullable = false)
    private Integer stock;

    @NotBlank(message = "El codigo no puede quedar vacio")
    @Size(max = 10, message = "El codigo no puede exceder los 10 caracteres")
    @Column(name = "codigo", length = 10,nullable = false,unique = true)
    private String codigo;

    @NotNull(message = "El precio no puede quedar vacio")
    @Column(name = "precio", precision = 11, scale = 2)
    private BigDecimal precio;
    @NotNull(message = "El costo no puede quedar vacio")
    @Column(name = "costo", precision = 11, scale = 2)
    private BigDecimal costo;

    @Column(name = "activo")
    private Boolean activo;

    @NotNull(message = "Debe seleccionar una categoria")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @NotNull(message = "Debe seleccionar una marca")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

}