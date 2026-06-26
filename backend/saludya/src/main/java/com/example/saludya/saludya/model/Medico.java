package com.example.saludya.saludya.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medicos_gen")
    @SequenceGenerator(name = "seq_medicos_gen", sequenceName = "seq_medicos", allocationSize = 1)
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_centro", nullable = false)
    private Long idCentro;

    @Column(name = "tipo_documento", length = 4, nullable = false)
    private String tipoDocumento = "CC";

    @Column(name = "cedula", length = 10, nullable = false, unique = true)
    private String cedula;

    @Column(name = "registro_medico", length = 30, unique = true)
    private String registroMedico;

    @Column(name = "especialidad", length = 60)
    private String especialidad;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "clave_hash", length = 255)
    private String claveHash;

    @Column(name = "estado", length = 10, nullable = false)
    private String estado = "ACTIVO";
}
