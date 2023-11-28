package br.com.consultorio.Service;

import br.com.consultorio.DTO.MedicoDTO;
import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundExcepction;
import br.com.consultorio.Mapper.MedicoMapper;
import br.com.consultorio.Model.Medico;
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

    public MedicoDTO create(MedicoDTO medicoDTO){
        validateMedico(medicoDTO);

        Medico medicoEntity = mapper.toEntity(medicoDTO);

        medicoRepository.save(medicoEntity);

        return mapper.toDto(medicoEntity);
    }

    public MedicoDTO update(MedicoDTO medicoDTO, Long id){
        findById(id);

        validateMedico(medicoDTO);

        Medico medicoEntity = mapper.toEntity(medicoDTO);

        medicoEntity.setId(id);

        medicoRepository.save(medicoEntity);

        return mapper.toDto(medicoEntity);
    }

    public void delete(Long id){
        medicoRepository.delete(mapper.toEntity(findById(id)));
    }

    //=============================================================================================

    public MedicoDTO findById(Long id){
        Medico medicoEntity = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExcepction("Médico com o id '" + id + "' não foi encontrado."));

        return mapper.toDto(medicoEntity);
    }

    public List<MedicoDTO> findByName(String name){
        System.out.println(name);
        return mapper.toDto(medicoRepository.findMedicosByName(name));
    }

    public List<MedicoDTO> findAll(){
        return mapper.toDto(medicoRepository.findAll());
    }

    //=============================================================================================

    public void validateMedico(MedicoDTO medicoDTO){
        Medico medicoEntity = mapper.toEntity(medicoDTO);
        Set<ConstraintViolation<Medico>> violations = validator.validate(medicoEntity);

        for (ConstraintViolation<Medico> violation : violations) {
            throw new BusinessException(violation.getMessage());
        }
    }


}