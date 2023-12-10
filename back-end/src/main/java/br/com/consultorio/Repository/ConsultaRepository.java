package br.com.consultorio.Repository;

import br.com.consultorio.Enumeration.ConsultaEnum;
import br.com.consultorio.Model.Consulta;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c.status_consulta FROM Consulta c WHERE c.agendamento.id = :idAgendamento")
    ConsultaEnum findStatusConsultaByAgendamento(Long idAgendamento);


    @Query("SELECT c FROM Consulta c " +
            "JOIN c.agendamento a " +
            "JOIN a.paciente pa " +
            "WHERE pa.CPF = :cpf " +
            "AND pa.data_nascimento = :dataNascimento")
    List<Consulta> findConsultasByPacienteCpfAndDataNascimento(
            @Param("cpf") String cpf,
            @Param("dataNascimento") Date dataNascimento);

}
