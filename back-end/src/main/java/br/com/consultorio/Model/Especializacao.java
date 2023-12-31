package br.com.consultorio.Model;

import br.com.consultorio.Enumeration.EspecializacaoEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
public class Especializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EspecializacaoEnum especializacao;

}

