package br.com.consultorio.Mapper;
import br.com.consultorio.DTO.MedicoDTO;
import br.com.consultorio.Model.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper extends EntityMapper<MedicoDTO, Medico> {
}
