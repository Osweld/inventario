package com.oswelddev.inventario.models.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Long id;

    @NotNull(message = "Debe seleccionar una persona como referencia")
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @Size(max = 15, message = "El código de identidad no puede exceder los 15 caracteres")
    @Column(name = "cod_identidad", length = 15)
    private String codigoIdentidad;

}