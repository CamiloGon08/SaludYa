package com.example.saludya.saludya.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonacionDTO {

    private Long idDonacion;
    private Long idDonante;
    private Long idCentro;
    private LocalDateTime fechaDonacion;
    private String tipo;
}
