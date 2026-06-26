package com.example.saludya.saludya.controller;

import com.example.saludya.saludya.dto.MenorDTO;
import com.example.saludya.saludya.service.MenorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menores")
@RequiredArgsConstructor
public class MenorController {

    private final MenorService menorService;

    @GetMapping
    public List<MenorDTO> listar() {
        return menorService.listarTodos();
    }

    @GetMapping("/{id}")
    public MenorDTO buscarPorId(@PathVariable Long id) {
        return menorService.buscarPorId(id);
    }

    @GetMapping("/paciente/{idPaciente}")
    public List<MenorDTO> listarPorPaciente(@PathVariable Long idPaciente) {
        return menorService.listarPorPaciente(idPaciente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenorDTO crear(@RequestBody MenorDTO dto) {
        return menorService.crear(dto);
    }

    @PutMapping("/{id}")
    public MenorDTO actualizar(@PathVariable Long id, @RequestBody MenorDTO dto) {
        return menorService.actualizar(id, dto);
    }
}
