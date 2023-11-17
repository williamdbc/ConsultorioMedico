package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
public class Paciente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos;
}
