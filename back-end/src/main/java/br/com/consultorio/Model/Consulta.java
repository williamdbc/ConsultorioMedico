package br.com.consultorio.Model;

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
    private String motivo_consulta;

    @Column
    private String resultado_consulta;

}