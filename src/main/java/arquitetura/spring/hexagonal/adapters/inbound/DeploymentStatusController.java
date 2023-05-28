package arquitetura.spring.hexagonal.adapters.inbound;

//import arquitetura.spring.hexagonal.adapters.inbound.helper.NullAwareBeanUtilsBean;

import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioRequestToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class DeploymentStatusController {

    @GetMapping
    public String deploy() {
        return "Deploy efetuado com sucesso!";
    }

}
