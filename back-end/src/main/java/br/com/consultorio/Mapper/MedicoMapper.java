package br.com.consultorio.Mapper;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Record.MedicoRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper extends EntityMapper<MedicoRecord, Medico> {
}
