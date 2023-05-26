package arquitetura.spring.hexagonal.application.core.service;

import arquitetura.spring.hexagonal.adapters.inbound.utils.FuncionarioUtils;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.SalvarFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarEnderecoPort;
import arquitetura.spring.hexagonal.application.ports.out.SalvarFuncionarioPort;

public class SalvarFuncionarioService implements SalvarFuncionarioServicePort {

    private final SalvarFuncionarioPort salvarFuncionarioPort;

    private final BuscarEnderecoPort buscarEnderecoPort;


    public SalvarFuncionarioService(SalvarFuncionarioPort salvarFuncionarioPort, BuscarEnderecoPort buscarEnderecoPort) {
        this.salvarFuncionarioPort = salvarFuncionarioPort;
        this.buscarEnderecoPort = buscarEnderecoPort;
    }

    @Override
    public Funcionario salvarUsuario(Funcionario funcionario, String cep) {
        //Verifica se o campo cep foi preenchido, e esqueceu de inserir cidade estado ou bairro, nesse caso os colocando de acordo com a api
        if (FuncionarioUtils.precisaAtualizarEndereco(funcionario)) {
            var endereco = buscarEnderecoPort.buscar(cep);
            funcionario.setCidade(endereco.getCidade());
            funcionario.setEstado(endereco.getUf());
            funcionario.setBairro(endereco.getBairro());
        }
        return salvarFuncionarioPort.salvar(funcionario);
    }
}
