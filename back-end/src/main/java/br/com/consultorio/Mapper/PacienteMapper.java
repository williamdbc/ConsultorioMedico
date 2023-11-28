package br.com.consultorio.Mapper;

import br.com.consultorio.DTO.PacienteDTO;
import br.com.consultorio.Model.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper extends EntityMapper<PacienteDTO, Paciente> {
}
