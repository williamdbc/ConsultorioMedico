package br.com.consultorio.Service;

import br.com.consultorio.DTO.PacienteDTO;
import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundException;
import br.com.consultorio.Mapper.PacienteMapper;
import br.com.consultorio.Model.Paciente;
import br.com.consultorio.Repository.PacienteRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper mapper;
    private final Validator validator;

    public PacienteDTO create(PacienteDTO pacienteDTO){
        validatePaciente(pacienteDTO);

        Paciente pacienteEntity = mapper.toEntity(pacienteDTO);

        pacienteRepository.save(pacienteEntity);

        return mapper.toDto(pacienteEntity);
    }

    public PacienteDTO update(PacienteDTO pacienteDTO, Long id){
        findById(id);

        validatePaciente(pacienteDTO);

        Paciente pacienteEntity = mapper.toEntity(pacienteDTO);

        pacienteEntity.setId(id);

        pacienteRepository.save(pacienteEntity);

        return mapper.toDto(pacienteEntity);
    }

    public void delete(Long id){
        pacienteRepository.delete(mapper.toEntity(findById(id)));
    }

    //=============================================================================================

    public PacienteDTO findById(Long id){
        Paciente pacienteEntity = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente com o id '" + id + "' não foi encontrado."));

        return mapper.toDto(pacienteEntity);
    }

    public List<PacienteDTO> findByName(String name){
        return mapper.toDto(pacienteRepository.findPacientesByName(name));
    }


    public List<PacienteDTO> findAll(){
        return mapper.toDto(pacienteRepository.findAll());
    }

    //=============================================================================================

    public void validatePaciente(PacienteDTO pacienteDTO){
        Paciente pacienteEntity = mapper.toEntity(pacienteDTO);
        Set<ConstraintViolation<Paciente>> violations = validator.validate(pacienteEntity);

        for (ConstraintViolation<Paciente> violation : violations) {
            throw new BusinessException(violation.getMessage());
        }
    }


}
