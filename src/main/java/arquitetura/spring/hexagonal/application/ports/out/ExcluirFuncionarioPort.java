package arquitetura.spring.hexagonal.application.ports.out;

import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

public interface ExcluirFuncionarioPort {
    Funcionario excluir(Long id);
}
