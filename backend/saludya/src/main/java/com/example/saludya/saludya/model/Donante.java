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

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "donantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donante extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_donantes_gen")
    @SequenceGenerator(name = "seq_donantes_gen", sequenceName = "seq_donantes", allocationSize = 1)
    @Column(name = "id_donante")
    private Long idDonante;

    @Column(name = "cedula", length = 10, nullable = false, unique = true)
    private String cedula;

    @Column(name = "tipo_sangre", length = 3, nullable = false)
    private String tipoSangre;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "municipio", length = 50)
    private String municipio;

    @Column(name = "latitud", precision = 9, scale = 6)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 9, scale = 6)
    private BigDecimal longitud;

    @Column(name = "fecha_ultima_donacion")
    private LocalDate fechaUltimaDonacion;

    @Column(name = "disponible", length = 1, nullable = false)
    private String disponible = "S";
}