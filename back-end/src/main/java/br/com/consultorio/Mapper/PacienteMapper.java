package br.com.consultorio.Mapper;

import br.com.consultorio.DTO.PacienteDTO;
import br.com.consultorio.Model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AgendamentoMapper.class})
public interface PacienteMapper extends EntityMapper<PacienteDTO, Paciente> {
}
