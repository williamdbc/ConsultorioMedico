package br.com.consultorio.Mapper;

import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Record.AgendamentoRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper extends EntityMapper<AgendamentoRecord, Agendamento> {
}

