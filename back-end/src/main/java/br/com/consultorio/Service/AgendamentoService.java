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

    private static final int INTERVAL_MINUTES = 29;
    private static final int INTERVAL_SECONDS = 59;

    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO){
        agendamentoDTO.getEndTime(INTERVAL_MINUTES, INTERVAL_SECONDS);
        
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        
        validate(agendamentoDTO, agendamento);
        
        agendamentoRepository.save(agendamento);
        
        return agendamentoMapper.toDto(agendamento);
    }
    
    public AgendamentoDTO update(AgendamentoDTO agendamentoDTO, Long id){
        findById(id);
        
        agendamentoDTO.getEndTime(INTERVAL_MINUTES, INTERVAL_SECONDS);
        
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        
        validate(agendamentoDTO, agendamento);
        
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
    
    public List<AgendamentoDTO> findSchedulesBetweenDates(Timestamp startDateTime, Timestamp endDateTime){
        if (endDateTime.before(startDateTime)) {
            throw new BusinessException("A data de fim não pode ser anterior à data de início.");
        }
        
        return agendamentoMapper.toDto(agendamentoRepository.findBetweenDates(startDateTime, endDateTime));
    }
    
    public List<AgendamentoDTO> findAll(){
        return agendamentoMapper.toDto(agendamentoRepository.findAll());
    }

    //=============================================================================================
    
    private void validate(AgendamentoDTO agendamentoDTO, Agendamento agendamento){
        Medico medico = validateMedico(agendamentoDTO);
        Paciente paciente = validatePaciente(agendamentoDTO);
        Recepcionista recepcionista = validateRecepcionista(agendamentoDTO);
        
        isScheduleAvailable(agendamentoDTO, medico, paciente);
        
        agendamento.setMedico(medico);
        agendamento.setPaciente(paciente);
        agendamento.setRecepcionista(recepcionista);
    }
    
    private Medico validateMedico(AgendamentoDTO agendamentoDTO){
        MedicoDTO medicoDTO = medicoService.findById(agendamentoDTO.getMedico().getId());
        return medicoMapper.toEntity(medicoDTO);
    }
    
    private Paciente validatePaciente(AgendamentoDTO agendamentoDTO){
        PacienteDTO pacienteDTO = pacienteService.findById(agendamentoDTO.getPaciente().getId());
        return pacienteMapper.toEntity(pacienteDTO);
    }
    
    private Recepcionista validateRecepcionista(AgendamentoDTO agendamentoDTO){
        RecepcionistaDTO recepcionistaDTO = recepcionistaService.findById(agendamentoDTO.getRecepcionista().getId());
        return recepcionistaMapper.toEntity(recepcionistaDTO);
    }
    
    private void isScheduleAvailable(AgendamentoDTO agendamentoDTO, Medico medico, Paciente paciente){
        Timestamp scheduleStartDateTime = agendamentoDTO.getData_hora_inicio();
        Timestamp scheduleEndDateTime = agendamentoDTO.getData_hora_fim();
        
        if(!medico.isAvailable(scheduleStartDateTime, scheduleEndDateTime)){
            throw new BusinessException("Médico já tem um agendamento nesse hórario.");
        }
        
        if(!paciente.isAvailable(scheduleStartDateTime, scheduleEndDateTime)){
            throw new BusinessException("Paciente já tem um agendamento nesse hórario.");
        }
    }
    
}
