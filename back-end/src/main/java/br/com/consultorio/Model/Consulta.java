package br.com.consultorio.Model;

import br.com.consultorio.Enumeration.ConsultaEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Agendamento agendamento;

    @Column
    private String sintomas;

    @Column
    private String diagnostico;

    @Enumerated(EnumType.STRING)
    private ConsultaEnum status_consulta;

}