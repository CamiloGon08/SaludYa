package com.example.saludya.saludya.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios_pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPaciente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuarios_pacientes_gen")
    @SequenceGenerator(name = "seq_usuarios_pacientes_gen", sequenceName = "seq_usuarios_pacientes", allocationSize = 1)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eps", nullable = false)
    private Eps eps;

    @Column(name = "tipo_documento", length = 4, nullable = false)
    private String tipoDocumento = "CC";

    @Column(name = "cedula", length = 10, nullable = false, unique = true)
    private String cedula;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "municipio", length = 50)
    private String municipio;

    @Column(name = "clave_hash", length = 255)
    private String claveHash;

    @Column(name = "estado", length = 10, nullable = false)
    private String estado = "ACTIVO";
}
