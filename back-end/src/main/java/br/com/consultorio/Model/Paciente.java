package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Paciente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
