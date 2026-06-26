package com.example.saludya.saludya.controller;

import com.example.saludya.saludya.dto.EpsDTO;
import com.example.saludya.saludya.service.EpsService;
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
@RequestMapping("/api/eps")
@RequiredArgsConstructor
public class EpsController {

    private final EpsService epsService;

    @GetMapping
    public List<EpsDTO> listar() {
        return epsService.listarTodas();
    }

    @GetMapping("/{id}")
    public EpsDTO buscarPorId(@PathVariable Long id) {
        return epsService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EpsDTO crear(@RequestBody EpsDTO dto) {
        return epsService.crear(dto);
    }

    @PutMapping("/{id}")
    public EpsDTO actualizar(@PathVariable Long id, @RequestBody EpsDTO dto) {
        return epsService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        epsService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
