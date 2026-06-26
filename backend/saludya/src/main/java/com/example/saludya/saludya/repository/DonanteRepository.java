package com.example.saludya.saludya.repository;

import com.example.saludya.saludya.model.Donante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonanteRepository extends JpaRepository<Donante, Long> {

    Optional<Donante> findByCedula(String cedula);
}