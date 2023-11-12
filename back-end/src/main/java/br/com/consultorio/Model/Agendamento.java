package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp data_hora_inicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp data_hora_fim;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Recepcionista recepcionista;

    @Column
    private double valor_consulta;

}
