package arquitetura.spring.hexagonal.adapters.inbound.mapper;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioToFuncionarioEntityMapper {
     public FuncionarioEntity mapper(Funcionario funcionario){
          var funcionarioEntity = new FuncionarioEntity();
          BeanUtils.copyProperties(funcionario, funcionarioEntity);
          return funcionarioEntity;
     }

}
