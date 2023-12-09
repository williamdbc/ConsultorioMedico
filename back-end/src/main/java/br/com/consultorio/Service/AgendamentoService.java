package br.com.consultorio.Service;

import br.com.consultorio.DTO.*;
import br.com.consultorio.Exception.BusinessException;
import br.com.consultorio.Exception.EntityNotFoundException;
import br.com.consultorio.Mapper.AgendamentoMapper;
import br.com.consultorio.Mapper.MedicoMapper;
import br.com.consultorio.Mapper.PacienteMapper;
import br.com.consultorio.Mapper.RecepcionistaMapper;
import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Model.Paciente;
import br.com.consultorio.Model.Recepcionista;

import br.com.consultorio.Repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final MedicoService medicoService;
    private final MedicoMapper medicoMapper;

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    private final RecepcionistaMapper recepcionistaMapper;
    private final RecepcionistaService recepcionistaService;

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO){
        validate(agendamentoDTO);

        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);

        isScheduleAvailable(agendamento);

        agendamentoRepository.save(agendamento);
        
        return agendamentoMapper.toDto(agendamento);
    }

    public AgendamentoDTO update(AgendamentoDTO agendamentoDTO, Long id){
        findById(id);

        validate(agendamentoDTO);

        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);

        isScheduleAvailable(agendamento);

        agendamento.setId(id);
        
        agendamentoRepository.save(agendamento);
        
        return agendamentoMapper.toDto(agendamento);
    }
    
    public void delete(Long id){
        agendamentoRepository.delete(agendamentoMapper.toEntity(findById(id)));
    }
    
    //=============================================================================================
    
    public AgendamentoDTO findById(Long id){
        Agendamento agendamentoEntity = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com o id '" + id + "' não foi encontrado."));
        
        return agendamentoMapper.toDto(agendamentoEntity);
    }

    public List<AgendamentoDTO> findSchedulesBetweenDates(String startDateTime, String endDateTime){
        Timestamp startTimestamp = Timestamp.valueOf(startDateTime.replace("T", " "));
        Timestamp endTimestamp = Timestamp.valueOf(endDateTime.replace("T", " "));

        startTimestamp.setHours(startTimestamp.getHours() - 3);
        endTimestamp.setHours(endTimestamp.getHours() - 3);

        if (endTimestamp.before(startTimestamp)) {
            throw new BusinessException("A data de fim não pode ser anterior à data de início.");
        }

        return agendamentoMapper.toDto(agendamentoRepository.findBetweenDates(startTimestamp, endTimestamp));
    }

    
    public List<AgendamentoDTO> findAll(){
        return agendamentoMapper.toDto(agendamentoRepository.findAll());
    }

    //=============================================================================================
    
    private void validate(AgendamentoDTO agendamentoDTO){
        if (agendamentoDTO.getData_hora_fim().before(agendamentoDTO.getData_hora_inicio())) {
            throw new BusinessException("A data de fim não pode ser anterior à data de início.");
        }

        if(agendamentoDTO.isMedicoNull()){
            throw new BusinessException("Medico não pode ser nulo.");
        }

        if(agendamentoDTO.isPacienteNull()){
            throw new BusinessException("Paciente não pode ser nulo.");
        }

        if(agendamentoDTO.isRecepcionistaNull()){
            throw new BusinessException("Recepcionista não pode ser nulo.");
        }

    }

    private void isScheduleAvailable(Agendamento agendamento){
        Timestamp scheduleStartDateTime = agendamento.getData_hora_inicio();
        Timestamp scheduleEndDateTime = agendamento.getData_hora_fim();

        MedicoDTO medicoDTO = medicoService.findById(agendamento.getMedico().getId());
        Medico medico = medicoMapper.toEntity(medicoDTO);

        PacienteDTO pacienteDTO = pacienteService.findById(agendamento.getPaciente().getId());
        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        
        if(!medico.isAvailable(scheduleStartDateTime, scheduleEndDateTime)){
            throw new BusinessException("Médico já tem um agendamento nesse hórario.");
        }
        
        if(!paciente.isAvailable(scheduleStartDateTime, scheduleEndDateTime)){
            throw new BusinessException("Paciente já tem um agendamento nesse hórario.");
        }

        RecepcionistaDTO recepcionistaDTO = recepcionistaService.findById(agendamento.getRecepcionista().getId());
        Recepcionista recepcionista = recepcionistaMapper.toEntity(recepcionistaDTO);

        agendamento.setMedico(medico);
        agendamento.setPaciente(paciente);
        agendamento.setRecepcionista(recepcionista);
    }
    
}
