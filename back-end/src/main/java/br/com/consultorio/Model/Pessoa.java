package br.com.consultorio.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nome_completo;

    @Column(length = 11)
    private String telefone;

    @Column
    private String endereco;

    @Column
    @CPF(message = "CPF inválido.")
    private String CPF;

    @Temporal(value = TemporalType.DATE)
    private Date data_nascimento;

    @Column(length = 100)
    @Email(message = "Email inválido.")
    private String email;

}