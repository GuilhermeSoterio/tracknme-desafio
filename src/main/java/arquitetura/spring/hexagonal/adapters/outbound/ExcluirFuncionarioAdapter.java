package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.ExcluirFuncionarioServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class ExcluirFuncionarioAdapter implements ExcluirFuncionarioServicePort {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @Override
    @Transactional
    public Funcionario excluirFuncionario(Long id) {
        var funcionarioResponse =funcionarioEntityToFuncionarioMapper.mapper(funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException(id)));
        funcionarioRepository.deleteById(id);
        return funcionarioResponse;
    }
}
