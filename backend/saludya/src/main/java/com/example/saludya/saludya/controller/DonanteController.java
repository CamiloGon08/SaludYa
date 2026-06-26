package com.example.saludya.saludya.controller;

import com.example.saludya.saludya.dto.DonanteDTO;
import com.example.saludya.saludya.service.DonanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/donantes")
@RequiredArgsConstructor
public class DonanteController {

    private final DonanteService donanteService;

    @GetMapping
    public List<DonanteDTO> listar() {
        return donanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public DonanteDTO buscarPorId(@PathVariable Long id) {
        return donanteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DonanteDTO crear(@RequestBody DonanteDTO dto) {
        return donanteService.crear(dto);
    }

    @PutMapping("/{id}")
    public DonanteDTO actualizar(@PathVariable Long id, @RequestBody DonanteDTO dto) {
        return donanteService.actualizar(id, dto);
    }

    @PatchMapping("/{id}/disponibilidad")
    public DonanteDTO cambiarDisponibilidad(@PathVariable Long id, @RequestParam String valor) {
        return donanteService.cambiarDisponibilidad(id, valor);
    }
}
