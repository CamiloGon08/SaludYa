package com.example.saludya.saludya.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpsDTO {

    private Long idEps;
    private String codigo;
    private String nombre;
    private String nit;
    private String telefono;
    private String estado;
}
