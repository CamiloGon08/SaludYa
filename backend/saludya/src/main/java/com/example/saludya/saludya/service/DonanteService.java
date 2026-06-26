package com.example.saludya.saludya.service;

import com.example.saludya.saludya.dto.DonanteDTO;
import com.example.saludya.saludya.model.Donante;
import com.example.saludya.saludya.repository.DonanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonanteService {

    private final DonanteRepository donanteRepository;

    @Transactional(readOnly = true)
    public List<DonanteDTO> listarTodos() {
        return donanteRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DonanteDTO buscarPorId(Long id) {
        return toDTO(buscarEntidad(id));
    }

    @Transactional
    public DonanteDTO crear(DonanteDTO dto) {
        donanteRepository.findByCedula(dto.getCedula()).ifPresent(d -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un donante con cedula " + dto.getCedula());
        });

        Donante donante = new Donante();
        donante.setPrimerNombre(dto.getPrimerNombre());
        donante.setSegundoNombre(dto.getSegundoNombre());
        donante.setPrimerApellido(dto.getPrimerApellido());
        donante.setSegundoApellido(dto.getSegundoApellido());
        donante.setCedula(dto.getCedula());
        donante.setTipoSangre(dto.getTipoSangre());
        donante.setTelefono(dto.getTelefono());
        donante.setCorreo(dto.getCorreo());
        donante.setMunicipio(dto.getMunicipio());
        donante.setLatitud(dto.getLatitud());
        donante.setLongitud(dto.getLongitud());
        donante.setDisponible(dto.getDisponible() != null ? dto.getDisponible() : "S");
        return toDTO(donanteRepository.save(donante));
    }

    @Transactional
    public DonanteDTO actualizar(Long id, DonanteDTO dto) {
        Donante donante = buscarEntidad(id);
        donante.setPrimerNombre(dto.getPrimerNombre());
        donante.setSegundoNombre(dto.getSegundoNombre());
        donante.setPrimerApellido(dto.getPrimerApellido());
        donante.setSegundoApellido(dto.getSegundoApellido());
        donante.setTipoSangre(dto.getTipoSangre());
        donante.setTelefono(dto.getTelefono());
        donante.setCorreo(dto.getCorreo());
        donante.setMunicipio(dto.getMunicipio());
        donante.setLatitud(dto.getLatitud());
        donante.setLongitud(dto.getLongitud());
        return toDTO(donante);
    }

    @Transactional
    public DonanteDTO cambiarDisponibilidad(Long id, String disponible) {
        Donante donante = buscarEntidad(id);
        donante.setDisponible(disponible);
        return toDTO(donante);
    }

    private Donante buscarEntidad(Long id) {
        return donanteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el donante con id " + id));
    }

    private DonanteDTO toDTO(Donante donante) {
        return DonanteDTO.builder()
                .idDonante(donante.getIdDonante())
                .primerNombre(donante.getPrimerNombre())
                .segundoNombre(donante.getSegundoNombre())
                .primerApellido(donante.getPrimerApellido())
                .segundoApellido(donante.getSegundoApellido())
                .cedula(donante.getCedula())
                .tipoSangre(donante.getTipoSangre())
                .telefono(donante.getTelefono())
                .correo(donante.getCorreo())
                .municipio(donante.getMunicipio())
                .latitud(donante.getLatitud())
                .longitud(donante.getLongitud())
                .fechaUltimaDonacion(donante.getFechaUltimaDonacion())
                .disponible(donante.getDisponible())
                .fechaRegistro(donante.getFechaRegistro())
                .build();
    }
}