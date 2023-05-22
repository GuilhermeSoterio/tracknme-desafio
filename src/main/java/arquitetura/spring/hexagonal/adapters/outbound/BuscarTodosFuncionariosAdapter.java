package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloIdPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class BuscarTodosFuncionariosAdapter implements BuscarTodosFuncionariosPort {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @Override
    @Transactional
    public List<Funcionario> buscarTodos() {
        return funcionarioEntityToFuncionarioMapper.mapper(funcionarioRepository.findAll());
    }
}
