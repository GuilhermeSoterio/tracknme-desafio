package arquitetura.spring.hexagonal.application.ports.out;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

public interface EditarFuncionarioPort {
    Funcionario editar(Long id, FuncionarioRequest funcionario);
}
