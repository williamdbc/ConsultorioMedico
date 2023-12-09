package br.com.consultorio.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PacienteDTO extends PessoaDTO{

    private Long id;

    @JsonIgnore
    private List<AgendamentoDTO> agendamentos;

}
