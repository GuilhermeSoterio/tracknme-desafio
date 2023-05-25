package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EditarFuncionarioAdapter implements EditarFuncionarioPort {

    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToUsuarioMapper;


    @Override
    public Funcionario editar(Long id, FuncionarioRequest funcionario) {
        FuncionarioEntity funcionarioEdit = funcionarioRepository.findById(id).orElseThrow(
                () -> new FuncionarioNotFoundException(id));
        if (funcionarioEdit != null) {
            funcionarioEdit.setNome(funcionario.getNome());
            funcionarioEdit.setIdade(funcionario.getIdade());
            funcionarioEdit.setCep(funcionario.getCep());
            funcionarioEdit.setCidade(funcionario.getCidade());
            funcionarioEdit.setEstado(funcionario.getEstado());
            funcionarioEdit.setBairro(funcionario.getBairro());
        }
        funcionarioRepository.save(funcionarioEdit);
        return funcionarioEntityToUsuarioMapper.mapper(funcionarioRepository.save(funcionarioEdit));
    }
}
