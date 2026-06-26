package com.example.saludya.saludya.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioPacienteDTO {

    private Long idPaciente;
    private Long idEps;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoDocumento;
    private String cedula;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    private String direccion;
    private String municipio;
    private String estado;
    private LocalDateTime fechaRegistro;
}
