package com.example.saludya.saludya.service;

import com.example.saludya.saludya.dto.EpsDTO;
import com.example.saludya.saludya.model.Eps;
import com.example.saludya.saludya.repository.EpsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpsService {

    private final EpsRepository epsRepository;

    @Transactional(readOnly = true)
    public List<EpsDTO> listarTodas() {
        return epsRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public EpsDTO buscarPorId(Long id) {
        return toDTO(buscarEntidad(id));
    }

    @Transactional
    public EpsDTO crear(EpsDTO dto) {
        Eps eps = new Eps();
        eps.setCodigo(dto.getCodigo());
        eps.setNombre(dto.getNombre());
        eps.setNit(dto.getNit());
        eps.setTelefono(dto.getTelefono());
        eps.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVA");
        return toDTO(epsRepository.save(eps));
    }

    @Transactional
    public EpsDTO actualizar(Long id, EpsDTO dto) {
        Eps eps = buscarEntidad(id);
        eps.setCodigo(dto.getCodigo());
        eps.setNombre(dto.getNombre());
        eps.setNit(dto.getNit());
        eps.setTelefono(dto.getTelefono());
        eps.setEstado(dto.getEstado());
        return toDTO(eps);
    }

    @Transactional
    public void desactivar(Long id) {
        buscarEntidad(id).setEstado("INACTIVA");
    }

    private Eps buscarEntidad(Long id) {
        return epsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la EPS con id " + id));
    }

    private EpsDTO toDTO(Eps eps) {
        return EpsDTO.builder()
                .idEps(eps.getIdEps())
                .codigo(eps.getCodigo())
                .nombre(eps.getNombre())
                .nit(eps.getNit())
                .telefono(eps.getTelefono())
                .estado(eps.getEstado())
                .build();
    }
}
