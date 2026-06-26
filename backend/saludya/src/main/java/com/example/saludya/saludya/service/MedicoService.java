package com.example.saludya.saludya.service;

import com.example.saludya.saludya.dto.MedicoDTO;
import com.example.saludya.saludya.model.Medico;
import com.example.saludya.saludya.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    public List<MedicoDTO> listarTodos() {
        return medicoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MedicoDTO buscarPorId(Long id) {
        return toDTO(buscarEntidad(id));
    }

    @Transactional
    public MedicoDTO crear(MedicoDTO dto) {
        medicoRepository.findByCedula(dto.getCedula()).ifPresent(m -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un medico con cedula " + dto.getCedula());
        });
        if (dto.getRegistroMedico() != null) {
            medicoRepository.findByRegistroMedico(dto.getRegistroMedico()).ifPresent(m -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un medico con registro " + dto.getRegistroMedico());
            });
        }

        Medico medico = new Medico();
        medico.setIdCentro(dto.getIdCentro());
        medico.setPrimerNombre(dto.getPrimerNombre());
        medico.setSegundoNombre(dto.getSegundoNombre());
        medico.setPrimerApellido(dto.getPrimerApellido());
        medico.setSegundoApellido(dto.getSegundoApellido());
        medico.setTipoDocumento(dto.getTipoDocumento() != null ? dto.getTipoDocumento() : "CC");
        medico.setCedula(dto.getCedula());
        medico.setRegistroMedico(dto.getRegistroMedico());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setTelefono(dto.getTelefono());
        medico.setCorreo(dto.getCorreo());
        medico.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO");
        return toDTO(medicoRepository.save(medico));
    }

    @Transactional
    public MedicoDTO actualizar(Long id, MedicoDTO dto) {
        Medico medico = buscarEntidad(id);
        medico.setIdCentro(dto.getIdCentro());
        medico.setPrimerNombre(dto.getPrimerNombre());
        medico.setSegundoNombre(dto.getSegundoNombre());
        medico.setPrimerApellido(dto.getPrimerApellido());
        medico.setSegundoApellido(dto.getSegundoApellido());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setTelefono(dto.getTelefono());
        medico.setCorreo(dto.getCorreo());
        return toDTO(medico);
    }

    @Transactional
    public void desactivar(Long id) {
        buscarEntidad(id).setEstado("INACTIVO");
    }

    private Medico buscarEntidad(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el medico con id " + id));
    }

    private MedicoDTO toDTO(Medico medico) {
        return MedicoDTO.builder()
                .idMedico(medico.getIdMedico())
                .idCentro(medico.getIdCentro())
                .primerNombre(medico.getPrimerNombre())
                .segundoNombre(medico.getSegundoNombre())
                .primerApellido(medico.getPrimerApellido())
                .segundoApellido(medico.getSegundoApellido())
                .tipoDocumento(medico.getTipoDocumento())
                .cedula(medico.getCedula())
                .registroMedico(medico.getRegistroMedico())
                .especialidad(medico.getEspecialidad())
                .telefono(medico.getTelefono())
                .correo(medico.getCorreo())
                .estado(medico.getEstado())
                .fechaRegistro(medico.getFechaRegistro())
                .build();
    }
}
