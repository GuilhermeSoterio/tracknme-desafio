package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import arquitetura.spring.hexagonal.application.ports.out.EditarParcialmenteFuncionarioPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class EditarFuncionarioParcialmenteAdapter implements EditarParcialmenteFuncionarioPort {

    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @Override
    public Funcionario editar(Long id, Map<String, Object> camposParciais) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException(id));

            if (camposParciais.containsKey("sexo")) {
                Integer sexoInt = (Integer) camposParciais.get("sexo");
                Sexo[] sexos = Sexo.values();
                if (sexoInt >= 0 && sexoInt < sexos.length) {
                    Sexo sexo = sexos[sexoInt];
                    funcionario.setSexo(sexo);
                }

            if (camposParciais.containsKey("nome")) funcionario.setNome((String) camposParciais.get("nome"));
            if (camposParciais.containsKey("idade")) funcionario.setIdade((Integer) camposParciais.get("idade"));
            if (camposParciais.containsKey("cidade")) funcionario.setCidade((String) camposParciais.get("cidade"));
            if (camposParciais.containsKey("estado")) funcionario.setEstado((String) camposParciais.get("estado"));
            if (camposParciais.containsKey("bairro")) funcionario.setBairro((String) camposParciais.get("bairro"));
            if (camposParciais.containsKey("cep")) funcionario.setCep((String) camposParciais.get("cep"));
            funcionarioRepository.save(funcionario);
        }
        return funcionarioEntityToFuncionarioMapper.mapper(funcionario);
    }
}
