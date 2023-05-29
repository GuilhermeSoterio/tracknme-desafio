package arquitetura.spring.hexagonal.adapters.inbound.exception;

import io.grpc.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioNotFoundExceptionTest {
    @Test
    public void testConstructorWithId() {
        // Arrange
        Long id = 1L;

        // Act
        FuncionarioNotFoundException exception = new FuncionarioNotFoundException(id);

        // Assert
        assertEquals(String.format("Funcionario com ID %s não encontrado.", id), exception.getMessage());
        assertEquals(Status.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testConstructorWithCep() {
        // Arrange
        String cep = "12345678";

        // Act
        FuncionarioNotFoundException exception = new FuncionarioNotFoundException(cep);

        // Assert
        assertEquals(String.format("Nenhum funcionário encontrado com o CEP: %s", cep), exception.getMessage());
        assertEquals(Status.NOT_FOUND, exception.getStatusCode());
    }
}