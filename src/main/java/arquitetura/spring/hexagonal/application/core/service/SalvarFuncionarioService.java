package arquitetura.spring.hexagonal.application.core.service;


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

        if(funcionario.getCep() != null && funcionario.getBairro() == null
        && funcionario.getCidade() == null && funcionario.getEstado() == null){
            var endereco = buscarEnderecoPort.buscar(cep);
            funcionario.setCidade(endereco.getCidade());
            funcionario.setEstado(endereco.getUf());
            funcionario.setBairro(endereco.getBairro());
        }
        return salvarFuncionarioPort.salvar(funcionario);
    }
}
