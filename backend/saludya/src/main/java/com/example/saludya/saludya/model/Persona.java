package com.example.saludya.saludya.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Persona {

    @Column(name = "primer_nombre", length = 30, nullable = false)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 30)
    private String segundoNombre;

    @Column(name = "primer_apellido", length = 30, nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 30)
    private String segundoApellido;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public String getNombreCompleto() {
        StringBuilder nombreCompleto = new StringBuilder(primerNombre);
        if (segundoNombre != null && !segundoNombre.isBlank()) {
            nombreCompleto.append(" ").append(segundoNombre);
        }
        nombreCompleto.append(" ").append(primerApellido);
        if (segundoApellido != null && !segundoApellido.isBlank()) {
            nombreCompleto.append(" ").append(segundoApellido);
        }
        return nombreCompleto.toString();
    }
}