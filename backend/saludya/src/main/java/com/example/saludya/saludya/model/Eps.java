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
@Table(name = "eps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Eps {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_eps_gen")
    @SequenceGenerator(name = "seq_eps_gen", sequenceName = "seq_eps", allocationSize = 1)
    @Column(name = "id_eps")
    private Long idEps;

    @Column(name = "codigo", length = 10)
    private String codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "nit", length = 15)
    private String nit;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "estado", length = 10, nullable = false)
    private String estado = "ACTIVA";
}
