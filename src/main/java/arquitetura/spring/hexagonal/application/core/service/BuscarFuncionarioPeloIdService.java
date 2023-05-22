package arquitetura.spring.hexagonal.application.core.service;


import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.BuscarFuncionarioPeloIdServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloIdPort;

public class BuscarFuncionarioPeloIdService implements BuscarFuncionarioPeloIdServicePort {

    private final BuscarFuncionarioPeloIdPort buscarFuncionarioPeloIdPort;

    public BuscarFuncionarioPeloIdService(BuscarFuncionarioPeloIdPort buscarFuncionarioPeloIdPort) {
        this.buscarFuncionarioPeloIdPort = buscarFuncionarioPeloIdPort;
    }
    @Override
    public Funcionario buscarPeloId(Long id) {
        return buscarFuncionarioPeloIdPort.BuscarPeloId(id);
    }
}
