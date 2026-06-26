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
@Table(name = "menores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menor extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menores_gen")
    @SequenceGenerator(name = "seq_menores_gen", sequenceName = "seq_menores", allocationSize = 1)
    @Column(name = "id_menor")
    private Long idMenor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private UsuarioPaciente paciente;

    @Column(name = "tipo_documento", length = 4, nullable = false)
    private String tipoDocumento = "RC";

    @Column(name = "numero_documento", length = 11)
    private String numeroDocumento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "sexo", length = 1)
    private String sexo;
}
