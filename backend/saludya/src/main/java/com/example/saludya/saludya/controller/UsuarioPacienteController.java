package com.example.saludya.saludya.controller;

import com.example.saludya.saludya.dto.UsuarioPacienteDTO;
import com.example.saludya.saludya.service.UsuarioPacienteService;
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
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class UsuarioPacienteController {

    private final UsuarioPacienteService usuarioPacienteService;

    @GetMapping
    public List<UsuarioPacienteDTO> listar() {
        return usuarioPacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioPacienteDTO buscarPorId(@PathVariable Long id) {
        return usuarioPacienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioPacienteDTO crear(@RequestBody UsuarioPacienteDTO dto) {
        return usuarioPacienteService.crear(dto);
    }

    @PutMapping("/{id}")
    public UsuarioPacienteDTO actualizar(@PathVariable Long id, @RequestBody UsuarioPacienteDTO dto) {
        return usuarioPacienteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        usuarioPacienteService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
