package br.com.consultorio.DTO;

import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Especializacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MedicoDTO extends PessoaDTO {

    private Long id;
    private String CRM;
    private Especializacao especializacao;

    @JsonIgnore
    private List<AgendamentoDTO> agendamentos;

}
