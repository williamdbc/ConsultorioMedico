package br.com.consultorio.Record;

import br.com.consultorio.Model.*;

import java.util.Date;
import java.util.List;

public record MedicoRecord(
        Long id,
        String nome_completo,
        String telefone,
        Endereco endereco,
        String CPF,
        Date data_nascimento,
        String email,
        String CRM,
        Especializacao especializacao,
        List<Agendamento> agendamentos
){
}
