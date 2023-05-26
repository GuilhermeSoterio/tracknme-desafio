package arquitetura.spring.hexagonal.adapters.inbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
//import arquitetura.spring.hexagonal.adapters.inbound.helper.NullAwareBeanUtilsBean;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioRequestToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.*;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import arquitetura.spring.hexagonal.application.ports.out.EditarParcialmenteFuncionarioPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
@AllArgsConstructor
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;
    private final ExcluirFuncionarioServicePort excluirFuncionarioServicePort;

    private final SalvarFuncionarioServicePort salvarFuncionarioServicePort;

    private final BuscarFuncionarioPeloIdServicePort buscarFuncionarioPeloIdServicePort;

    private final BuscarTodosFuncionariosServicePort buscarTodosFuncionariosServicePort;

    private final EditarFuncionarioServicePort editarFuncionarioPort;

    private final BuscarFuncionarioPeloCEPServicePort buscarFuncionarioPeloCEPPort;

    private final EditarParcialmenteFuncionarioServicePort editarParcialmenteFuncionarioPort;

    private final FuncionarioRequestToFuncionarioMapper funcionarioRequestToFuncionarioMapper;

    @PostMapping
    public Funcionario salvarFuncionario(@RequestBody FuncionarioRequest usuarioRequest) {
        var funcionario = funcionarioRequestToFuncionarioMapper.mapper(usuarioRequest);
        return salvarFuncionarioServicePort.salvarUsuario(funcionario, usuarioRequest.getCep());
    }

    @GetMapping("/{id}")
    public Funcionario buscarPeloIdFuncionario(@PathVariable("id") Long id) {
        return buscarFuncionarioPeloIdServicePort.buscarPeloId(id);
    }

    @GetMapping
    public List<Funcionario> buscarTodosFuncionarios() {
        return buscarTodosFuncionariosServicePort.buscarTodos();
    }

    @GetMapping("/cep/{cep}")
    public List<Funcionario> BuscarFuncionariosPeloCEP(@PathVariable("cep") String cep) {
        return buscarFuncionarioPeloCEPPort.buscarPeloCEP(cep);
    }

    @PutMapping("/{id}")
    public Funcionario editarFuncionario(@PathVariable("id") Long id, @RequestBody FuncionarioRequest funcionario) {
        return editarFuncionarioPort.editarUsuario(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public Funcionario excluirFuncionario(@PathVariable("id") Long id) {
        return excluirFuncionarioServicePort.excluirFuncionario(id);
    }

    @PatchMapping("/{id}")
    public Funcionario patchUpdate(@PathVariable("id") Long id, @RequestBody Map<String, Object> camposAtualizados) {
        return editarParcialmenteFuncionarioPort.editarParcialmente(id, camposAtualizados);
    }
}
