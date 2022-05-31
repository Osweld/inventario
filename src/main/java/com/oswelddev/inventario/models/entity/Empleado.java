package com.oswelddev.inventario.models.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false)
    private Long id;

    @NotNull(message = "Debe seleccionar una persona como referencia")
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @NotBlank(message = "El usuario de empleado no puede quedar vacio")
    @Size(max = 15, message = "El usuario de empleado no puede exceder los 15 caracteres")
    @Column(name = "usuario", length = 15, nullable = false)
    private String usuario;

    @NotBlank(message = "La contraseña del empleado no puede quedar vacia")
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @NotBlank(message = "El código de empleado no puede quedar vacio")
    @Size(max = 15, message = "El código de empleado no puede exceder los 15 caracteres")
    @Column(name = "cod_empleado", length = 15)
    private String codigoEmpleado;

}