package br.com.consultorio.Record;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

public record AgendamentoRecord(
        Long id,
        Timestamp data_hora_inicio,
        Timestamp data_hora_fim,
        MedicoRecord medico,
        PacienteRecord paciente,
        RecepcionistaRecord recepcionista,
        double valor_consulta
) {
    public AgendamentoRecord{
        data_hora_fim = Timestamp.from(data_hora_inicio.toInstant().plus(29, ChronoUnit.MINUTES).plus(59, ChronoUnit.SECONDS));
    }
}
