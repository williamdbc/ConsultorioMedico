package br.com.consultorio.Mapper;

import br.com.consultorio.Model.Paciente;
import br.com.consultorio.Record.PacienteRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper extends EntityMapper<PacienteRecord, Paciente> {
}
