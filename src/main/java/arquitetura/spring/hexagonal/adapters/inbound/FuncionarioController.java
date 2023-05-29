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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        return salvarFuncionarioServicePort.salvarFuncionario(funcionario, usuarioRequest.getCep());
    }

    @GetMapping("/{id}")
    @Cacheable(value = "FuncionariosPorId", key = "#id")
    public Funcionario buscarPeloIdFuncionario(@PathVariable("id") Long id) {
        return buscarFuncionarioPeloIdServicePort.buscarPeloId(id);
    }

    @GetMapping
    @Cacheable("Funcionarios")
    public List<Funcionario> buscarTodosFuncionarios() {
        System.out.println("Sem cache");
        return buscarTodosFuncionariosServicePort.buscarTodos();
    }

    @GetMapping("/cep/{cep}")
    @Cacheable(value = "FuncionariosPorCEP", key = "#cep")
    public List<Funcionario> BuscarFuncionariosPeloCEP(@PathVariable("cep") String cep) {
        return buscarFuncionarioPeloCEPPort.buscarPeloCEP(cep);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = {"Funcionarios", "FuncionariosPorId", "FuncionariosPorCEP"}, allEntries = true)
    public Funcionario editarFuncionario(@PathVariable("id") Long id, @RequestBody FuncionarioRequest funcionario) {
        return editarFuncionarioPort.editarFuncionario(id, funcionario);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"Funcionarios", "FuncionariosPorId", "FuncionariosPorCEP"}, allEntries = true)
    public Funcionario excluirFuncionario(@PathVariable("id") Long id) {
        return excluirFuncionarioServicePort.excluirFuncionario(id);
    }

    @PatchMapping("/{id}")
    @CacheEvict(value = {"Funcionarios", "FuncionariosPorId", "FuncionariosPorCEP"}, allEntries = true)
    public Funcionario patchUpdate(@PathVariable("id") Long id, @RequestBody Map<String, Object> camposAtualizados) {
        return editarParcialmenteFuncionarioPort.editarParcialmente(id, camposAtualizados);
    }
}
