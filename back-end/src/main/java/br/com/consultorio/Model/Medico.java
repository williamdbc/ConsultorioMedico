package br.com.consultorio.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    @JoinColumn(name = "id_especializacao")
    private Especializacao especializacao;

    @OneToMany(mappedBy = "medico")
    @JsonIgnore
    private List<Agendamento> agendamentos;


}
