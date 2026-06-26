package com.example.saludya.saludya.service;

import com.example.saludya.saludya.dto.MenorDTO;
import com.example.saludya.saludya.model.Menor;
import com.example.saludya.saludya.model.UsuarioPaciente;
import com.example.saludya.saludya.repository.MenorRepository;
import com.example.saludya.saludya.repository.UsuarioPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenorService {

    private final MenorRepository menorRepository;
    private final UsuarioPacienteRepository usuarioPacienteRepository;

    @Transactional(readOnly = true)
    public List<MenorDTO> listarTodos() {
        return menorRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MenorDTO buscarPorId(Long id) {
        return toDTO(buscarEntidad(id));
    }

    @Transactional(readOnly = true)
    public List<MenorDTO> listarPorPaciente(Long idPaciente) {
        return menorRepository.findByPaciente_IdPaciente(idPaciente).stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public MenorDTO crear(MenorDTO dto) {
        Menor menor = new Menor();
        menor.setPaciente(buscarPaciente(dto.getIdPaciente()));
        menor.setPrimerNombre(dto.getPrimerNombre());
        menor.setSegundoNombre(dto.getSegundoNombre());
        menor.setPrimerApellido(dto.getPrimerApellido());
        menor.setSegundoApellido(dto.getSegundoApellido());
        menor.setTipoDocumento(dto.getTipoDocumento() != null ? dto.getTipoDocumento() : "RC");
        menor.setNumeroDocumento(dto.getNumeroDocumento());
        menor.setFechaNacimiento(dto.getFechaNacimiento());
        menor.setSexo(dto.getSexo());
        return toDTO(menorRepository.save(menor));
    }

    @Transactional
    public MenorDTO actualizar(Long id, MenorDTO dto) {
        Menor menor = buscarEntidad(id);
        menor.setPrimerNombre(dto.getPrimerNombre());
        menor.setSegundoNombre(dto.getSegundoNombre());
        menor.setPrimerApellido(dto.getPrimerApellido());
        menor.setSegundoApellido(dto.getSegundoApellido());
        menor.setNumeroDocumento(dto.getNumeroDocumento());
        menor.setFechaNacimiento(dto.getFechaNacimiento());
        menor.setSexo(dto.getSexo());
        return toDTO(menor);
    }

    private Menor buscarEntidad(Long id) {
        return menorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el menor con id " + id));
    }

    private UsuarioPaciente buscarPaciente(Long idPaciente) {
        return usuarioPacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el paciente con id " + idPaciente));
    }

    private MenorDTO toDTO(Menor menor) {
        return MenorDTO.builder()
                .idMenor(menor.getIdMenor())
                .idPaciente(menor.getPaciente().getIdPaciente())
                .primerNombre(menor.getPrimerNombre())
                .segundoNombre(menor.getSegundoNombre())
                .primerApellido(menor.getPrimerApellido())
                .segundoApellido(menor.getSegundoApellido())
                .tipoDocumento(menor.getTipoDocumento())
                .numeroDocumento(menor.getNumeroDocumento())
                .fechaNacimiento(menor.getFechaNacimiento())
                .sexo(menor.getSexo())
                .fechaRegistro(menor.getFechaRegistro())
                .build();
    }
}
