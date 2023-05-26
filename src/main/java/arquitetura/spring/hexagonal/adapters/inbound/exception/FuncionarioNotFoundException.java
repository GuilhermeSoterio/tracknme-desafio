package arquitetura.spring.hexagonal.adapters.inbound.exception;

import io.grpc.Status;

public class FuncionarioNotFoundException extends BaseBusinessException{

    private static final String ERROR_MESSAGE = "Funcionario com ID %s não encontrado.";
    private static final String ERROR_MESSAGE_CEP = "Nenhum funcionário encontrado com o CEP: %s";
    private final Long id;
    private final String cep;

    public FuncionarioNotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
        this.cep = null;
    }

    public FuncionarioNotFoundException(String cep) {
        super(String.format(ERROR_MESSAGE_CEP, cep));
        this.id = null;
        this.cep = cep;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        if (id != null) {
            return String.format(ERROR_MESSAGE, id);
        } else {
            return String.format(ERROR_MESSAGE_CEP, cep);
        }
    }
}
