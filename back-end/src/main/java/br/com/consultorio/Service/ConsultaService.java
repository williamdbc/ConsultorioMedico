package br.com.consultorio.Service;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.DTO.ConsultaDTO;
import br.com.consultorio.Enumeration.ConsultaEnum;
import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundException;
import br.com.consultorio.Mapper.ConsultaMapper;
import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Consulta;
import br.com.consultorio.Repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaDTO update(ConsultaDTO consultaDTO, Long id){
        consultaDTO.setAgendamento(findById(id).getAgendamento());

        validate(consultaDTO);

        Consulta consulta = consultaMapper.toEntity(consultaDTO);
        consulta.setId(id);

        consulta.setStatus_consulta(ConsultaEnum.valueOf(consultaDTO.getStatus_consulta()));
        consultaRepository.save(consulta);

        return consultaMapper.toDto(consulta);
    }

    public ConsultaDTO findById(Long id){
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta com o id '" + id + "' não foi encontrado."));

        return consultaMapper.toDto(consulta);
    }

    public List<ConsultaDTO> findConsultasByCPF(String cpf, String birthDateString){


        Date birthDate = convertParaData(birthDateString);
        return consultaMapper.toDto(consultaRepository.findConsultasByPacienteCpfAndDataNascimento(cpf,birthDate));
    }


    public List<ConsultaDTO> findAll(){
        return consultaMapper.toDto(consultaRepository.findAll());
    }

    private Date convertParaData(String dataString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(dataString);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void validate(ConsultaDTO consultaDTO){
        if(consultaDTO.isStatusNull()){
            throw new BusinessException("O status informado não pode ser nulo.");
        }

        consultaDTO.consultaToUpperCase();

        if(!consultaDTO.isStatusValid()){
            throw new BusinessException("O status informado não existe.");
        }

    }

}
