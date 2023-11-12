package br.com.consultorio.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nome_completo;

    @Column(length = 11)
    private String telefone;

    @OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
    private Endereco endereco;

    @Column
    @CPF
    private String CPF;

    @Temporal(value = TemporalType.DATE)
    private Date data_nascimento;

    @Column(length = 100)
    @Email
    private String email;

}