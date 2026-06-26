package com.example.saludya.saludya.controller;

import com.example.saludya.saludya.dto.MedicoDTO;
import com.example.saludya.saludya.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public List<MedicoDTO> listar() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public MedicoDTO buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoDTO crear(@RequestBody MedicoDTO dto) {
        return medicoService.crear(dto);
    }

    @PutMapping("/{id}")
    public MedicoDTO actualizar(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        return medicoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        medicoService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
