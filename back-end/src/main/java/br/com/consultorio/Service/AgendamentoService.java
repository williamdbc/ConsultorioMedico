package br.com.consultorio.Service;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.DTO.PacienteDTO;
import br.com.consultorio.Mapper.AgendamentoMapper;
import br.com.consultorio.Mapper.MedicoMapper;
import br.com.consultorio.Mapper.PacienteMapper;
import br.com.consultorio.Mapper.RecepcionistaMapper;
import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Model.Recepcionista;
import br.com.consultorio.Repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final AgendamentoMapper mapper;


    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO){

        Agendamento agendamento = mapper.toEntity(agendamentoDTO);

        return mapper.toDto(agendamento);
    }


}
