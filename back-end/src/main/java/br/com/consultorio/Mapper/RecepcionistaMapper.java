package br.com.consultorio.Mapper;

import br.com.consultorio.Model.Medico;
import br.com.consultorio.Model.Recepcionista;
import br.com.consultorio.Record.MedicoRecord;
import br.com.consultorio.Record.RecepcionistaRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecepcionistaMapper extends EntityMapper<RecepcionistaRecord, Recepcionista> {
}