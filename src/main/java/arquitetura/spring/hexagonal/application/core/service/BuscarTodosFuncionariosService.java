package arquitetura.spring.hexagonal.application.core.service;


import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.BuscarTodosFuncionariosServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;

import java.util.List;

public class BuscarTodosFuncionariosService implements BuscarTodosFuncionariosServicePort {

    private final BuscarTodosFuncionariosPort buscarTodosFuncionariosPort;

    public BuscarTodosFuncionariosService(BuscarTodosFuncionariosPort buscarTodosFuncionariosPort) {
        this.buscarTodosFuncionariosPort = buscarTodosFuncionariosPort;
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return buscarTodosFuncionariosPort.buscarTodos();
    }
}
