package br.com.consultorio.Repository;

import br.com.consultorio.Model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE a.data_hora_inicio BETWEEN :startDateTime AND :endDateTime")
    List<Agendamento> findBetweenDates(@Param("startDateTime") Timestamp startDateTime,
                                       @Param("endDateTime") Timestamp endDateTime);

}
