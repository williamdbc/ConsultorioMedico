package br.com.consultorio.Mapper;

import br.com.consultorio.DTO.RecepcionistaDTO;
import br.com.consultorio.Model.Recepcionista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecepcionistaMapper extends EntityMapper<RecepcionistaDTO, Recepcionista> {
}