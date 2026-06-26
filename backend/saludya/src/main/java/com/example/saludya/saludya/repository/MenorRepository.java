package com.example.saludya.saludya.repository;

import com.example.saludya.saludya.model.Menor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenorRepository extends JpaRepository<Menor, Long> {

    List<Menor> findByPaciente_IdPaciente(Long idPaciente);
}
