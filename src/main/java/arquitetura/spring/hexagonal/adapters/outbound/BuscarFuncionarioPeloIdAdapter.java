package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloIdPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class BuscarFuncionarioPeloIdAdapter implements BuscarFuncionarioPeloIdPort {

    private final FuncionarioRepository usuarioRepository;
    private final FuncionarioEntityToFuncionarioMapper usuarioEntityToUsuarioMapper;

    @Override
    @Transactional
    public Funcionario BuscarPeloId(Long id) {
        return usuarioEntityToUsuarioMapper.mapper(usuarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException(id)));
    }
}
