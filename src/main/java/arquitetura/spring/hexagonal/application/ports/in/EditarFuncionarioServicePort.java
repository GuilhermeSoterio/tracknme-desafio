package arquitetura.spring.hexagonal.application.ports.in;


import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

public interface EditarFuncionarioServicePort {
    Funcionario editarUsuario(Funcionario usuario);
}
