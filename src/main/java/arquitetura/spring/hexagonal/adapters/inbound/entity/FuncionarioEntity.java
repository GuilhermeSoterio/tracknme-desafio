package arquitetura.spring.hexagonal.adapters.inbound.entity;

import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    private String nome;

    private Integer idade;

    private Sexo sexo;

    private String cep;

    private String bairro;

    private String estado;

    private String cidade;


    public FuncionarioEntity(String nome, String bairro, int idade, String cep, Sexo sexo, String cidade, String estado) {
        this.nome = nome;
        this.bairro = bairro;
        this.idade = idade;
        this.cep = cep;
        this.sexo = sexo;
        this.cidade = cidade;
        this.estado = estado;
    }

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "endereco_id")
//    private EnderecoEntity endereco;

}
