package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.mapper.EnderecoToEnderecoEntityMapper;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioToFuncionarioEntityMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.out.SalvarFuncionarioPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class SalvarFuncionarioAdapter implements SalvarFuncionarioPort {

    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioToFuncionarioEntityMapper funcionarioToFuncionarioEntityMapper;

    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToUsuarioMapper;

    @Override
    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        var funcionarioEntity = funcionarioToFuncionarioEntityMapper.mapper(funcionario);

        return funcionarioEntityToUsuarioMapper.mapper(funcionarioRepository.save(funcionarioEntity));
    }
}
