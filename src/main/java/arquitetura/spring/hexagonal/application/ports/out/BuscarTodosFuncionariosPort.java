package arquitetura.spring.hexagonal.application.ports.out;


import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

import java.util.List;

public interface BuscarTodosFuncionariosPort {
    List<Funcionario> buscarTodos();
}
