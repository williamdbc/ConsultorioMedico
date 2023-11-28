package br.com.consultorio.Mapper;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.Model.Agendamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper extends EntityMapper<AgendamentoDTO, Agendamento> {
}

