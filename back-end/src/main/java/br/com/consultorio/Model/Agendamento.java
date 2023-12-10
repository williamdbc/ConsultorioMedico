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
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_recepcionista")
    private Recepcionista recepcionista;

    @OneToOne(mappedBy = "agendamento", cascade = CascadeType.ALL)
    private Consulta consulta;

    @Column
    private double valor_consulta;

}
