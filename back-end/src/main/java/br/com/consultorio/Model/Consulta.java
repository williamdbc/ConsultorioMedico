package br.com.consultorio.Model;

import br.com.consultorio.Enumeration.ConsultaEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_agendamento")
    private Agendamento agendamento;

    @Column
    private String sintomas;

    @Column
    private String diagnostico;

    @Column
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private ConsultaEnum status_consulta;

    public Consulta(Agendamento agendamento){
        this.agendamento = agendamento;
        this.setStatus_consulta(ConsultaEnum.AGENDADA);
        this.sintomas = "";
        this.diagnostico = "";
        this.observacoes = "";
    }

}