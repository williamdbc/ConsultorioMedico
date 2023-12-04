package br.com.consultorio.DTO;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

public record AgendamentoRecord(
		Long id,
		Timestamp data_hora_inicio,
		Timestamp data_hora_fim,
		MedicoDTO medico,
		PacienteDTO paciente,
		RecepcionistaDTO recepcionista,
		double valor_consulta
){
	
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
