package br.com.consultorio.Mapper;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.Model.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class, PacienteMapper.class, RecepcionistaMapper.class})
public interface AgendamentoMapper extends EntityMapper<AgendamentoDTO, Agendamento> {

    @Override
    @Mapping(target = "medico.agendamentos", expression = "java(null)")
    @Mapping(target = "paciente.agendamentos", expression = "java(null)")
    Agendamento toEntity(AgendamentoDTO dto);

    @Override
    @Mapping(target = "medico.agendamentos", expression = "java(null)")
    @Mapping(target = "paciente.agendamentos", expression = "java(null)")
    AgendamentoDTO toDto(Agendamento entity);
}

