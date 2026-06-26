package com.example.saludya.saludya.repository;

import com.example.saludya.saludya.model.Eps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpsRepository extends JpaRepository<Eps, Long> {

    Optional<Eps> findByCodigo(String codigo);
}
