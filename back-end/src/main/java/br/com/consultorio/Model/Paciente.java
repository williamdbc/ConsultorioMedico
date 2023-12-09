package br.com.consultorio.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Paciente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<Agendamento> agendamentos;

    public boolean isAvailable(Timestamp startDateTimeSchedule, Timestamp endDateTimeSchedule) {
        return (this.agendamentos == null) ? true :
                this.agendamentos.stream()
                .noneMatch(agendamento ->
                agendamento.getData_hora_inicio().before(endDateTimeSchedule) &&
                agendamento.getData_hora_fim().after(startDateTimeSchedule));
    }

}
