package br.com.consultorio.DTO;

import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Especializacao;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MedicoDTO extends PessoaDTO {

    private Long id;
    private String CRM;
    private Especializacao especializacao;
    private List<AgendamentoDTO> agendamentos;

}
