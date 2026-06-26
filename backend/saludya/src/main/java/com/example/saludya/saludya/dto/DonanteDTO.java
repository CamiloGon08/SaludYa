package com.example.saludya.saludya.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonanteDTO {

    private Long idDonante;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String cedula;
    private String tipoSangre;
    private String telefono;
    private String correo;
    private String municipio;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private LocalDate fechaUltimaDonacion;
    private String disponible;
    private LocalDateTime fechaRegistro;
}