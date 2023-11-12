package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Medico extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String CRM;

    @ManyToOne
    private Especializacao especializacao;

    @OneToMany
    private List<Agendamento> agendamentos;

}
