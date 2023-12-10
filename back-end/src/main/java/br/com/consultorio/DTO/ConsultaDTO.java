package br.com.consultorio.DTO;

import br.com.consultorio.Enumeration.ConsultaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Arrays;

@Data
public class ConsultaDTO {

    private Long id;
    private AgendamentoDTO agendamento;
    private String sintomas;
    private String diagnostico;
    private String status_consulta;

    @JsonIgnore
    public boolean isStatusNull(){
        return status_consulta == null;
    }

    @JsonIgnore
    public boolean isStatusValid(){
        return Arrays.stream(ConsultaEnum.values())
                .anyMatch(status -> status.name().equalsIgnoreCase(status_consulta));
    }
    @JsonIgnore
    public void consultaToUpperCase(){
        status_consulta = status_consulta.toUpperCase();
    }

}
