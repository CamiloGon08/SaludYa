package com.example.saludya.saludya.repository;

import com.example.saludya.saludya.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCedula(String cedula);

    Optional<Medico> findByRegistroMedico(String registroMedico);
}
