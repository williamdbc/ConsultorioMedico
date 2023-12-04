package br.com.consultorio.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PacienteDTO extends PessoaDTO{

    private Long id;
    private List<AgendamentoDTO> agendamentos;

}
