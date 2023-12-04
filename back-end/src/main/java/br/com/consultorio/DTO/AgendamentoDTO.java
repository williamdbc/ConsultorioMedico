package br.com.consultorio.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Data
public class AgendamentoDTO {

    private Long id;
    private Timestamp data_hora_inicio;
    private Timestamp data_hora_fim;
    private MedicoDTO medico;
    private PacienteDTO paciente;
    private RecepcionistaDTO recepcionista;
    private double valor_consulta;

    public Timestamp getEndTime(int intervalMinutes, int intervalSeconds) {
            return Timestamp.from(data_hora_inicio.toInstant()
                    .plus(intervalMinutes, ChronoUnit.MINUTES)
                    .plus(intervalSeconds, ChronoUnit.SECONDS));
    }
    
}