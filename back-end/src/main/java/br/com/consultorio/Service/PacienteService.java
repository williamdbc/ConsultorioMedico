package br.com.consultorio.Service;

import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundExcepction;
import br.com.consultorio.Mapper.PacienteMapper;
import br.com.consultorio.Model.Paciente;
import br.com.consultorio.Record.MedicoRecord;
import br.com.consultorio.Record.PacienteRecord;
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

    public PacienteRecord create(PacienteRecord pacienteRecord){
        validatePaciente(pacienteRecord);

        Paciente pacienteEntity = mapper.toEntity(pacienteRecord);

        pacienteRepository.save(pacienteEntity);

        return mapper.toDto(pacienteEntity);
    }

    public PacienteRecord update(PacienteRecord pacienteRecord, Long id){
        findById(id);

        validatePaciente(pacienteRecord);

        Paciente pacienteEntity = mapper.toEntity(pacienteRecord);

        pacienteEntity.setId(id);

        pacienteRepository.save(pacienteEntity);

        return mapper.toDto(pacienteEntity);
    }

    public void delete(Long id){
        pacienteRepository.delete(mapper.toEntity(findById(id)));
    }

    //=============================================================================================

    public PacienteRecord findById(Long id){
        Paciente pacienteEntity = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExcepction("Paciente com o id '" + id + "' não foi encontrado."));

        return mapper.toDto(pacienteEntity);
    }

    public List<PacienteRecord> findByName(String name){
        return mapper.toDto(pacienteRepository.findPacientesByName(name));
    }


    public List<PacienteRecord> findAll(){
        return mapper.toDto(pacienteRepository.findAll());
    }

    //=============================================================================================

    public void validatePaciente(PacienteRecord pacienteRecord){
        Paciente pacienteEntity = mapper.toEntity(pacienteRecord);
        Set<ConstraintViolation<Paciente>> violations = validator.validate(pacienteEntity);

        for (ConstraintViolation<Paciente> violation : violations) {
            throw new BusinessException(violation.getMessage());
        }
    }


}
