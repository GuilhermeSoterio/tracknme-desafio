package arquitetura.spring.hexagonal.application.ports.in;

import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

import java.util.List;

public interface BuscarTodosFuncionariosServicePort {
    List<Funcionario> buscarTodos();
}
