package arquitetura.spring.hexagonal.application.ports.in;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

public interface EditarFuncionarioServicePort {
    Funcionario editarUsuario(Long id, FuncionarioRequest funcionario);
}
