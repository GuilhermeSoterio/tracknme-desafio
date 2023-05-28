package arquitetura.spring.hexagonal.adapters.inbound.request;

import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Integer idade;

    @NotBlank
    private String cep;

    private Sexo sexo;

    private String bairro;

    private String cidade;

    private String estado;

}
