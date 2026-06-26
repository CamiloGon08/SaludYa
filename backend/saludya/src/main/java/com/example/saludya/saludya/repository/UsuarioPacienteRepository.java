package com.example.saludya.saludya.repository;

import com.example.saludya.saludya.model.UsuarioPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioPacienteRepository extends JpaRepository<UsuarioPaciente, Long> {

    Optional<UsuarioPaciente> findByCedula(String cedula);
}
