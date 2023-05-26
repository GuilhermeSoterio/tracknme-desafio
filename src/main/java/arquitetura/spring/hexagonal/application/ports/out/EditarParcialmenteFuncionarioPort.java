package arquitetura.spring.hexagonal.application.ports.out;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

import java.util.Map;

public interface EditarParcialmenteFuncionarioPort {
    Funcionario editar(Long id, Map<String, Object> camposParciais);
}
