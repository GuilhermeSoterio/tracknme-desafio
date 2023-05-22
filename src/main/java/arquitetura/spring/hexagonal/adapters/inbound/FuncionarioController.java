package arquitetura.spring.hexagonal.adapters.inbound;

import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioRequestToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.request.BuscarPorIdFuncionarioRequest;
import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.BuscarFuncionarioPeloIdServicePort;
import arquitetura.spring.hexagonal.application.ports.in.SalvarFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@AllArgsConstructor
public class FuncionarioController {

    private final SalvarFuncionarioServicePort salvarUsuarioServicePort;

    private final BuscarFuncionarioPeloIdServicePort buscarFuncionarioPeloIdServicePort;

    private final BuscarTodosFuncionariosPort buscarTodosFuncionariosPort;

    private final BuscarFuncionarioPeloCEPPort buscarFuncionarioPeloCEPPort;

    private final FuncionarioRequestToFuncionarioMapper usuarioRequestToUsuarioMapper;

    @PostMapping
    public Funcionario salvarFuncionario(@RequestBody FuncionarioRequest usuarioRequest){
        var funcionario = usuarioRequestToUsuarioMapper.mapper(usuarioRequest);
        return salvarUsuarioServicePort.salvarUsuario(funcionario, usuarioRequest.getCep());
    }

    @GetMapping("/{id}")
    public Funcionario buscarPeloIdFuncionario(@PathVariable("id") Long id){
        return buscarFuncionarioPeloIdServicePort.buscarPeloId(id);
    }

    @GetMapping
    public List<Funcionario> buscarTodosFuncionariosPort(){
        return buscarTodosFuncionariosPort.buscarTodos();
    }

    @GetMapping("/cep/{cep}")
    public List<Funcionario> BuscarFuncionariosPeloCEP(@PathVariable("cep") String cep){
        return buscarFuncionarioPeloCEPPort.buscarFuncionarioCEP(cep);
    }

}
