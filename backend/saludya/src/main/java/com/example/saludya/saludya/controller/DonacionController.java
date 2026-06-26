package com.example.saludya.saludya.controller;

import com.example.saludya.saludya.dto.DonacionDTO;
import com.example.saludya.saludya.service.DonacionService;
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
@RequestMapping("/api/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final DonacionService donacionService;

    @GetMapping
    public List<DonacionDTO> listar() {
        return donacionService.listarTodas();
    }

    @GetMapping("/{id}")
    public DonacionDTO buscarPorId(@PathVariable Long id) {
        return donacionService.buscarPorId(id);
    }

    @GetMapping("/donante/{idDonante}")
    public List<DonacionDTO> listarPorDonante(@PathVariable Long idDonante) {
        return donacionService.listarPorDonante(idDonante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DonacionDTO crear(@RequestBody DonacionDTO dto) {
        return donacionService.crear(dto);
    }

    @PutMapping("/{id}")
    public DonacionDTO actualizar(@PathVariable Long id, @RequestBody DonacionDTO dto) {
        return donacionService.actualizar(id, dto);
    }
}
