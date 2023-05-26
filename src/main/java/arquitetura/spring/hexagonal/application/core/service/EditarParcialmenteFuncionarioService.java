package arquitetura.spring.hexagonal.application.core.service;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.ports.in.EditarParcialmenteFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarEnderecoPort;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import arquitetura.spring.hexagonal.application.ports.out.EditarParcialmenteFuncionarioPort;

import java.util.Map;

public class EditarParcialmenteFuncionarioService implements EditarParcialmenteFuncionarioServicePort {

    private final EditarParcialmenteFuncionarioPort editarParcialmenteFuncionarioPort;

    private final BuscarEnderecoPort buscarEnderecoPort;

    public EditarParcialmenteFuncionarioService(EditarParcialmenteFuncionarioPort editarParcialmenteFuncionarioPort,
                                                BuscarEnderecoPort buscarEnderecoPort) {
        this.editarParcialmenteFuncionarioPort = editarParcialmenteFuncionarioPort;
        this.buscarEnderecoPort = buscarEnderecoPort;
    }

    @Override
    public Funcionario editarParcialmente(Long id, Map<String, Object> camposParciais) {
        if(camposParciais.containsKey("cep")){
            var endereco = buscarEnderecoPort.buscar((String) camposParciais.get("cep"));
            camposParciais.put("cidade", endereco.getCidade());
            camposParciais.put("estado", endereco.getUf());
            camposParciais.put("bairro", endereco.getBairro());
        }
        return editarParcialmenteFuncionarioPort.editar(id, camposParciais);
    }
}
