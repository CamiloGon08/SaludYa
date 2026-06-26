package com.example.saludya.saludya.service;

import com.example.saludya.saludya.dto.UsuarioPacienteDTO;
import com.example.saludya.saludya.model.Eps;
import com.example.saludya.saludya.model.UsuarioPaciente;
import com.example.saludya.saludya.repository.EpsRepository;
import com.example.saludya.saludya.repository.UsuarioPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioPacienteService {

    private final UsuarioPacienteRepository usuarioPacienteRepository;
    private final EpsRepository epsRepository;

    @Transactional(readOnly = true)
    public List<UsuarioPacienteDTO> listarTodos() {
        return usuarioPacienteRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioPacienteDTO buscarPorId(Long id) {
        return toDTO(buscarEntidad(id));
    }

    @Transactional
    public UsuarioPacienteDTO crear(UsuarioPacienteDTO dto) {
        usuarioPacienteRepository.findByCedula(dto.getCedula()).ifPresent(p -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un paciente con cedula " + dto.getCedula());
        });

        UsuarioPaciente paciente = new UsuarioPaciente();
        paciente.setEps(buscarEps(dto.getIdEps()));
        paciente.setPrimerNombre(dto.getPrimerNombre());
        paciente.setSegundoNombre(dto.getSegundoNombre());
        paciente.setPrimerApellido(dto.getPrimerApellido());
        paciente.setSegundoApellido(dto.getSegundoApellido());
        paciente.setTipoDocumento(dto.getTipoDocumento() != null ? dto.getTipoDocumento() : "CC");
        paciente.setCedula(dto.getCedula());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setTelefono(dto.getTelefono());
        paciente.setCorreo(dto.getCorreo());
        paciente.setDireccion(dto.getDireccion());
        paciente.setMunicipio(dto.getMunicipio());
        paciente.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO");
        return toDTO(usuarioPacienteRepository.save(paciente));
    }

    @Transactional
    public UsuarioPacienteDTO actualizar(Long id, UsuarioPacienteDTO dto) {
        UsuarioPaciente paciente = buscarEntidad(id);
        paciente.setEps(buscarEps(dto.getIdEps()));
        paciente.setPrimerNombre(dto.getPrimerNombre());
        paciente.setSegundoNombre(dto.getSegundoNombre());
        paciente.setPrimerApellido(dto.getPrimerApellido());
        paciente.setSegundoApellido(dto.getSegundoApellido());
        paciente.setTipoDocumento(dto.getTipoDocumento());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setTelefono(dto.getTelefono());
        paciente.setCorreo(dto.getCorreo());
        paciente.setDireccion(dto.getDireccion());
        paciente.setMunicipio(dto.getMunicipio());
        return toDTO(paciente);
    }

    @Transactional
    public void desactivar(Long id) {
        buscarEntidad(id).setEstado("INACTIVO");
    }

    private UsuarioPaciente buscarEntidad(Long id) {
        return usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el paciente con id " + id));
    }

    private Eps buscarEps(Long idEps) {
        return epsRepository.findById(idEps)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la EPS con id " + idEps));
    }

    private UsuarioPacienteDTO toDTO(UsuarioPaciente paciente) {
        return UsuarioPacienteDTO.builder()
                .idPaciente(paciente.getIdPaciente())
                .idEps(paciente.getEps().getIdEps())
                .primerNombre(paciente.getPrimerNombre())
                .segundoNombre(paciente.getSegundoNombre())
                .primerApellido(paciente.getPrimerApellido())
                .segundoApellido(paciente.getSegundoApellido())
                .tipoDocumento(paciente.getTipoDocumento())
                .cedula(paciente.getCedula())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .telefono(paciente.getTelefono())
                .correo(paciente.getCorreo())
                .direccion(paciente.getDireccion())
                .municipio(paciente.getMunicipio())
                .estado(paciente.getEstado())
                .fechaRegistro(paciente.getFechaRegistro())
                .build();
    }
}
