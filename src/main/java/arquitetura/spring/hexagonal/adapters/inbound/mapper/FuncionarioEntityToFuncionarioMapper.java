package arquitetura.spring.hexagonal.adapters.inbound.mapper;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FuncionarioEntityToFuncionarioMapper {

     public Funcionario mapper(FuncionarioEntity funcionarioEntity){
          var funcionario = new Funcionario();
          BeanUtils.copyProperties(funcionarioEntity, funcionario);
          return funcionario;
     }


     public List<Funcionario> mapper(List<FuncionarioEntity> funcionarioEntities) {
          List<Funcionario> funcionarios = new ArrayList<>();

          for (FuncionarioEntity funcionarioEntity : funcionarioEntities) {
               Funcionario funcionario = new Funcionario();
               BeanUtils.copyProperties(funcionarioEntity, funcionario);
               funcionarios.add(funcionario);
          }

          return funcionarios;
     }
}
