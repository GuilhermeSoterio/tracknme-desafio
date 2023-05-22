package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloIdPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class BuscarFuncionarioPeloCEPAdapter implements BuscarFuncionarioPeloCEPPort {

    private final FuncionarioRepository usuarioRepository;
    private final FuncionarioEntityToFuncionarioMapper usuarioEntityToUsuarioMapper;

    @Override
    @Transactional
    public List<Funcionario> buscarFuncionarioCEP(String cep) {
        return usuarioEntityToUsuarioMapper.mapper(usuarioRepository.findByCep(cep));
    }
}
