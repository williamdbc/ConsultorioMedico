package br.com.consultorio.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.swing.text.html.parser.Entity;
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

    @JsonIgnore
    private ConsultaDTO consulta;

    @JsonIgnore
    public boolean isPacienteNull(){
       return isEntityNull(paciente);
   }

    @JsonIgnore
    public boolean isMedicoNull(){
        return isEntityNull(medico);
    }

    @JsonIgnore
    public boolean isRecepcionistaNull(){
        return isEntityNull(recepcionista);
    }

   private boolean isEntityNull(Object entity){
       return entity == null;
   }

}