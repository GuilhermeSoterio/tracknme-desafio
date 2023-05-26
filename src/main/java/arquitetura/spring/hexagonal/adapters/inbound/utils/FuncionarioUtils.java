package arquitetura.spring.hexagonal.adapters.inbound.utils;

import arquitetura.spring.hexagonal.application.core.domain.Funcionario;

public class FuncionarioUtils {
    public static boolean precisaAtualizarEndereco(Funcionario funcionario) {
        return funcionario.getCep() != null &&
                ((funcionario.getBairro() == null || funcionario.getBairro().trim().isEmpty())
                        || (funcionario.getCidade() == null || funcionario.getCidade().trim().isEmpty())
                        || (funcionario.getEstado() == null || funcionario.getEstado().trim().isEmpty()));
    }
}
