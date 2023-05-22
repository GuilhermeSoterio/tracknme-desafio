package arquitetura.spring.hexagonal.application.ports.out;


import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

public interface SalvarFuncionarioPort {
    Funcionario salvar(Funcionario usuario);
}
