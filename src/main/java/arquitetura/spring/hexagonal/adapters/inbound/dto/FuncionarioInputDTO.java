package arquitetura.spring.hexagonal.adapters.inbound.dto;

import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class FuncionarioInputDTO {

    private String nome;

    private Integer idade;

    private String cep;

    private Sexo sexo;

    private String bairro;

    private String cidade;

    private String estado;

}
