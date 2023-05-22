package arquitetura.spring.hexagonal.adapters.inbound.entity;

import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "endereco_id")
//    private EnderecoEntity endereco;

}
