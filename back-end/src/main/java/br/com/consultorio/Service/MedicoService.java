package br.com.consultorio.Service;

import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundExcepction;
import br.com.consultorio.Mapper.MedicoMapper;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Record.MedicoRecord;
import br.com.consultorio.Repository.EnderecoRepository;
import br.com.consultorio.Repository.MedicoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper mapper;
    private final Validator validator;

    public MedicoRecord create(MedicoRecord medicoRecord){
        validateMedico(medicoRecord);

        Medico medicoEntity = mapper.toEntity(medicoRecord);

        medicoRepository.save(medicoEntity);

        return mapper.toDto(medicoEntity);
    }


    public MedicoRecord update(MedicoRecord medicoRecord, Long id){
        if(!medicoRepository.existsById(id)){
            throw new EntityNotFoundExcepction("Médico não encontrado.");
        }

        validateMedico(medicoRecord);

        Medico medicoEntity = mapper.toEntity(medicoRecord);

        medicoEntity.setId(id);

        medicoRepository.save(medicoEntity);

        return mapper.toDto(medicoEntity);
    }


    public void delete(Long id){
        if(!medicoRepository.existsById(id)){
            throw new EntityNotFoundExcepction("Médico não encontrado.");
        }

        medicoRepository.deleteById(id);
    }


    public MedicoRecord findById(Long id){
        if(!medicoRepository.existsById(id)){
            throw new EntityNotFoundExcepction("Médico não encontrado.");
        }

        Medico medicoEntity = medicoRepository.findById(id).get();
        return mapper.toDto(medicoEntity);
    }

    public List<MedicoRecord> findAll(){
        return mapper.toDto(medicoRepository.findAll());
    }


    public void validateMedico(MedicoRecord medicoRecord){
        Medico medicoEntity = mapper.toEntity(medicoRecord);
        Set<ConstraintViolation<Medico>> violations = validator.validate(medicoEntity);

        for (ConstraintViolation<Medico> violation : violations) {
            throw new BusinessException(violation.getMessage());
        }
    }


}