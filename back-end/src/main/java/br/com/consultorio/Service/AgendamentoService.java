package br.com.consultorio.Service;

import br.com.consultorio.Mapper.AgendamentoMapper;
import br.com.consultorio.Mapper.MedicoMapper;
import br.com.consultorio.Mapper.PacienteMapper;
import br.com.consultorio.Mapper.RecepcionistaMapper;
import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Model.Recepcionista;
import br.com.consultorio.Record.AgendamentoRecord;
import br.com.consultorio.Record.MedicoRecord;
import br.com.consultorio.Record.PacienteRecord;
import br.com.consultorio.Record.RecepcionistaRecord;
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


    public AgendamentoRecord create(AgendamentoRecord agendamentoRecord){

        MedicoRecord medicoRecord = medicoService.findById(agendamentoRecord.medico().id());
        PacienteRecord pacienteRecord = pacienteService.findById(agendamentoRecord.paciente().id());
        RecepcionistaRecord recepcionistaRecord = recepcionistaService.findById(agendamentoRecord.recepcionista().id());

        Agendamento agendamento = mapper.toEntity(agendamentoRecord);

        agendamento.setMedico(medicoMapper.toEntity(medicoRecord));
        agendamento.setPaciente(pacienteMapper.toEntity(pacienteRecord));
        agendamento.setRecepcionista(recepcionistaMapper.toEntity(recepcionistaRecord));


        //agendamento.setMedico(mamedicoService.findById(agendamentoRecord.medico().id()));

        return mapper.toDto(agendamento);
    }


}
