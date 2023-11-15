package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
public class Recepcionista extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
