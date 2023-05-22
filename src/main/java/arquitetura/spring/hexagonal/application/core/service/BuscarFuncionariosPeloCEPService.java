package arquitetura.spring.hexagonal.application.core.service;


import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.BuscarFuncionarioPeloCEPServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;

import java.util.List;

public class BuscarFuncionariosPeloCEPService implements BuscarFuncionarioPeloCEPServicePort {

    private final BuscarFuncionarioPeloCEPPort buscarFuncionarioPeloCEPPort;

    public BuscarFuncionariosPeloCEPService(BuscarFuncionarioPeloCEPPort buscarFuncionarioPeloCEPPort) {
        this.buscarFuncionarioPeloCEPPort = buscarFuncionarioPeloCEPPort;
    }

    @Override
    public List<Funcionario> buscarPeloCEP(String cep) {
        return buscarFuncionarioPeloCEPPort.buscarFuncionarioCEP(cep);
    }
}
