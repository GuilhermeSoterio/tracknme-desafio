package arquitetura.spring.hexagonal.adapters.inbound.mapper;


import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioRequestToFuncionarioMapper {

    public Funcionario mapper(FuncionarioRequest funcionarioRequest){
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioRequest, funcionario);
        return funcionario;
    }

}
