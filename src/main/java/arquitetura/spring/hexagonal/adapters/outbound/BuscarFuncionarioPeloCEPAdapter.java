package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class BuscarFuncionarioPeloCEPAdapter implements BuscarFuncionarioPeloCEPPort {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @Override
    @Transactional
    public List<Funcionario> buscarFuncionarioCEP(String cep) {
        List<FuncionarioEntity> funcionarios = funcionarioRepository.findByCep(cep);
        if (funcionarios.isEmpty()) {
            throw new FuncionarioNotFoundException("Nenhum funcionário encontrado com o CEP: " + cep);
        }
        return funcionarioEntityToFuncionarioMapper.mapper(funcionarios);
    }
}
