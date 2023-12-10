package br.com.consultorio.Mapper;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.DTO.ConsultaDTO;
import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = "spring", uses = {AgendamentoMapper.class})
public interface ConsultaMapper extends EntityMapper<ConsultaDTO, Consulta> {

}
