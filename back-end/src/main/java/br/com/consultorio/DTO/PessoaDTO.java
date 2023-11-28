package br.com.consultorio.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class PessoaDTO {

    private Long id;
    private String nome_completo;
    private String telefone;
    private String endereco;
    private String CPF;
    private Date data_nascimento;
    private String email;

}
