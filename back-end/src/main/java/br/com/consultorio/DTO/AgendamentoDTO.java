package br.com.consultorio.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Data
public class AgendamentoDTO {

    private Long id;
    private Timestamp data_hora_inicio;
    private Timestamp data_hora_fim;
    private MedicoDTO medicoDTO;
    private PacienteDTO pacienteDTO;
    private RecepcionistaDTO recepcionistaDTO;
    private double valor_consulta;

    public Timestamp getEndTime(Timestamp hora_inicial) {
        return Timestamp.from(hora_inicial.toInstant().plus(29, ChronoUnit.MINUTES).plus(59, ChronoUnit.SECONDS));
    }

    public boolean startDateGreatherThenEndDate(){
        return data_hora_inicio.after(data_hora_fim);
    }

    public boolean differeceInDaysGreatherThan(){
        Long differenceBetweenDays = 60L;
        return ChronoUnit.DAYS.between(data_hora_fim.toInstant(), data_hora_inicio.toInstant()) > differenceBetweenDays;
    }


}
