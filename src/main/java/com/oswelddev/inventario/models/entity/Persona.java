package com.oswelddev.inventario.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede quedar vacio")
    @Size(max = 60, message = "El nombre no puede exceder los 60 caracteres")
    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;

    @NotBlank(message = "Los apellidos no puede quedar vacios")
    @Size(max = 60, message = "Los apellidos no pueden exceder los 60 caracteres")
    @Column(name = "apellidos", length = 60, nullable = false)
    private String apellidos;

    @NotBlank(message = "El número de identidad no puede quedar vacio")
    @Size(max = 10, message = "El número de identidad no puede exceder los 10 caracteres")
    @Column(name = "no_identidad", length = 10, nullable = false, unique = true)
    private String noIdentidad;

    @NotBlank(message = "La fecha de nacimiento no puede quedar vacio")
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @NotBlank(message = "El número de telefono no puede quedar vacio")
    @Size(max = 9, message = "El número de telefono no puede exceder los 9 caracteres")
    @Column(name = "telefono", length = 9)
    private String telefono;

    @Column(name = "activo")
    private Boolean activo;

}