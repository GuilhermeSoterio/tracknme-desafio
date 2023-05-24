package arquitetura.spring.hexagonal.adapters.inbound.exception;

import io.grpc.Status;

public class FuncionarioNotFoundException extends BaseBusinessException{

    private static final String ERROR_MESSAGE = "Funcionario com ID %s não encontrado.";
    private final Long id;

    public FuncionarioNotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return String.format(ERROR_MESSAGE, this.id);
    }
}
