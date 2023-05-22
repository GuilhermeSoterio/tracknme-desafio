package arquitetura.spring.hexagonal.adapters.inbound.request;

import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioRequest {

    private String nome;

    private Integer idade;

    private String cep;

    private Sexo sexo;

    private String bairro;

    private String cidade;

    private String estado;

}
