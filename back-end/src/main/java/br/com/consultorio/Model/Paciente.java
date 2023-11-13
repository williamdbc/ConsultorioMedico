package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Paciente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean plano_saude;
    
    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos;
}
