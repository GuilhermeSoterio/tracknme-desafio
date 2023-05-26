package arquitetura.spring.hexagonal.application.ports.in;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

import java.util.Map;

public interface EditarParcialmenteFuncionarioServicePort {
    Funcionario editarParcialmente(Long id, Map<String, Object> camposParciais);
}
