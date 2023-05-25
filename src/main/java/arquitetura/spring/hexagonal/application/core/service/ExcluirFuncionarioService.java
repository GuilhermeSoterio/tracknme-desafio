package arquitetura.spring.hexagonal.application.core.service;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.EditarFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.in.ExcluirFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import arquitetura.spring.hexagonal.application.ports.out.ExcluirFuncionarioPort;

public class ExcluirFuncionarioService implements ExcluirFuncionarioServicePort {

    private final ExcluirFuncionarioPort excluirFuncionarioPort;


    public ExcluirFuncionarioService(ExcluirFuncionarioPort excluirFuncionarioPort) {
        this.excluirFuncionarioPort = excluirFuncionarioPort;
    }

    @Override
    public Funcionario excluirFuncionario(Long id) {
        return excluirFuncionarioPort.excluir(id);
    }
}
