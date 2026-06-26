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

import java.time.LocalDateTime;

@Entity
@Table(name = "donaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_donaciones_gen")
    @SequenceGenerator(name = "seq_donaciones_gen", sequenceName = "seq_donaciones", allocationSize = 1)
    @Column(name = "id_donacion")
    private Long idDonacion;

    @Column(name = "id_donante", nullable = false)
    private Long idDonante;

    @Column(name = "id_centro", nullable = false)
    private Long idCentro;

    @Column(name = "fecha_donacion", nullable = false)
    private LocalDateTime fechaDonacion = LocalDateTime.now();

    @Column(name = "tipo", length = 10, nullable = false)
    private String tipo = "NORMAL";
}
