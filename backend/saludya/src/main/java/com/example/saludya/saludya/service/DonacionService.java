package com.example.saludya.saludya.service;

import com.example.saludya.saludya.dto.DonacionDTO;
import com.example.saludya.saludya.model.Donacion;
import com.example.saludya.saludya.repository.DonacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final DonacionRepository donacionRepository;

    @Transactional(readOnly = true)
    public List<DonacionDTO> listarTodas() {
        return donacionRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DonacionDTO buscarPorId(Long id) {
        return toDTO(buscarEntidad(id));
    }

    @Transactional(readOnly = true)
    public List<DonacionDTO> listarPorDonante(Long idDonante) {
        return donacionRepository.findByIdDonante(idDonante).stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public DonacionDTO crear(DonacionDTO dto) {
        Donacion donacion = new Donacion();
        donacion.setIdDonante(dto.getIdDonante());
        donacion.setIdCentro(dto.getIdCentro());
        donacion.setFechaDonacion(dto.getFechaDonacion() != null ? dto.getFechaDonacion() : LocalDateTime.now());
        donacion.setTipo(dto.getTipo() != null ? dto.getTipo() : "NORMAL");
        return toDTO(donacionRepository.save(donacion));
    }

    @Transactional
    public DonacionDTO actualizar(Long id, DonacionDTO dto) {
        Donacion donacion = buscarEntidad(id);
        donacion.setIdDonante(dto.getIdDonante());
        donacion.setIdCentro(dto.getIdCentro());
        donacion.setFechaDonacion(dto.getFechaDonacion());
        donacion.setTipo(dto.getTipo());
        return toDTO(donacion);
    }

    private Donacion buscarEntidad(Long id) {
        return donacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la donacion con id " + id));
    }

    private DonacionDTO toDTO(Donacion donacion) {
        return DonacionDTO.builder()
                .idDonacion(donacion.getIdDonacion())
                .idDonante(donacion.getIdDonante())
                .idCentro(donacion.getIdCentro())
                .fechaDonacion(donacion.getFechaDonacion())
                .tipo(donacion.getTipo())
                .build();
    }
}
