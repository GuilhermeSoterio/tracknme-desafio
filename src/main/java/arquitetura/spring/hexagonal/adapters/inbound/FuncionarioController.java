package arquitetura.spring.hexagonal.adapters.inbound;

import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioRequestToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.request.BuscarPorIdFuncionarioRequest;
import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.BuscarFuncionarioPeloIdServicePort;
import arquitetura.spring.hexagonal.application.ports.in.ExcluirFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.in.SalvarFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@AllArgsConstructor
public class FuncionarioController {

    private final ExcluirFuncionarioServicePort excluirFuncionarioServicePort;

    private final SalvarFuncionarioServicePort salvarFuncionarioServicePort;

    private final BuscarFuncionarioPeloIdServicePort buscarFuncionarioPeloIdServicePort;

    private final BuscarTodosFuncionariosPort buscarTodosFuncionariosPort;

    private final EditarFuncionarioPort editarFuncionarioPort;

    private final BuscarFuncionarioPeloCEPPort buscarFuncionarioPeloCEPPort;

    private final FuncionarioRequestToFuncionarioMapper funcionarioRequestToFuncionarioMapper;

    @PostMapping
    public Funcionario salvarFuncionario(@RequestBody FuncionarioRequest usuarioRequest){
        var funcionario = funcionarioRequestToFuncionarioMapper.mapper(usuarioRequest);
        return salvarFuncionarioServicePort.salvarUsuario(funcionario, usuarioRequest.getCep());
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

    @PutMapping("/{id}")
    public Funcionario editarFuncionario(@PathVariable("id") Long id, @RequestBody FuncionarioRequest funcionario){
        return editarFuncionarioPort.editar(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public Funcionario excluirFuncionario(@PathVariable("id") Long id){
        return excluirFuncionarioServicePort.excluirFuncionario(id);
    }

}
