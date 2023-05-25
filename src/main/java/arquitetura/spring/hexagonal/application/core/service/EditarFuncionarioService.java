package arquitetura.spring.hexagonal.application.core.service;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.EditarFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarEnderecoPort;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import arquitetura.spring.hexagonal.application.ports.out.SalvarFuncionarioPort;

public class EditarFuncionarioService implements EditarFuncionarioServicePort {

    private final EditarFuncionarioPort editarFuncionarioPort;


    public EditarFuncionarioService(EditarFuncionarioPort editarFuncionarioPort) {
        this.editarFuncionarioPort = editarFuncionarioPort;
    }

    @Override
    public Funcionario editarUsuario(Long id, FuncionarioRequest funcionario) {
        return editarFuncionarioPort.editar(id, funcionario);
    }
}
