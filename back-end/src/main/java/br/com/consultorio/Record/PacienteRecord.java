package br.com.consultorio.Record;

import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Endereco;

import java.util.Date;
import java.util.List;

public record PacienteRecord(
        Long id,
        String nome_completo,
        String telefone,
        Endereco endereco,
        String CPF,
        Date data_nascimento,
        String email,
        boolean plano_saude,
        List<Agendamento> agendamentos
) {

}
