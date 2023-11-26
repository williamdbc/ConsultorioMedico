package br.com.consultorio.Service;

import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundExcepction;
import br.com.consultorio.Mapper.RecepcionistaMapper;
import br.com.consultorio.Model.Paciente;
import br.com.consultorio.Model.Recepcionista;
import br.com.consultorio.Record.PacienteRecord;
import br.com.consultorio.Record.RecepcionistaRecord;
import br.com.consultorio.Repository.RecepcionistaRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RecepcionistaService {

    private final RecepcionistaRepository recepcionistaRepository;
    private final RecepcionistaMapper mapper;
    private final Validator validator;

    public RecepcionistaRecord create(RecepcionistaRecord recepcionistaRecord){
        validateRecepcionista(recepcionistaRecord);

        Recepcionista recepcionistaEntity = mapper.toEntity(recepcionistaRecord);

        recepcionistaRepository.save(recepcionistaEntity);

        return mapper.toDto(recepcionistaEntity);
    }

    public RecepcionistaRecord update(RecepcionistaRecord recepcionistaRecord, Long id){
        findById(id);

        validateRecepcionista(recepcionistaRecord);

        Recepcionista recepcionistaEntity = mapper.toEntity(recepcionistaRecord);

        recepcionistaEntity.setId(id);

        recepcionistaRepository.save(recepcionistaEntity);

        return mapper.toDto(recepcionistaEntity);
    }

    public void delete(Long id){
        recepcionistaRepository.delete(mapper.toEntity(findById(id)));
    }

    //=============================================================================================

    public RecepcionistaRecord findById(Long id){
        Recepcionista recepcionistaEntity = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExcepction("Paciente com o id '" + id + "' não foi encontrado."));

        return mapper.toDto(recepcionistaEntity);
    }

    public List<RecepcionistaRecord> findByName(String name){
        return mapper.toDto(recepcionistaRepository.findRecepcionistasByName(name));
    }

    public List<RecepcionistaRecord> findAll(){
        return mapper.toDto(recepcionistaRepository.findAll());
    }

    //=============================================================================================

    public void validateRecepcionista(RecepcionistaRecord recepcionistaRecord){
        Recepcionista recepcionistaEntity = mapper.toEntity(recepcionistaRecord);
        Set<ConstraintViolation<Recepcionista>> violations = validator.validate(recepcionistaEntity);

        for (ConstraintViolation<Recepcionista> violation : violations) {
            throw new BusinessException(violation.getMessage());
        }
    }

}
