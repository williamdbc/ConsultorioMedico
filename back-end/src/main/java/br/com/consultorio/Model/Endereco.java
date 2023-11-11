package br.com.consultorio.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column //@CEP
    private String CEP;

    @Column
    private String unidade_federativa;

    @Column
    private String cidade;

    @Column
    private String bairro;

    @Column
    private String rua;

    @Column
    private int numero;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Pessoa pessoa;

}