package arquitetura.spring.hexagonal.adapters.inbound.mapper;

import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioRequestToFuncionarioMapperTest {
    private FuncionarioRequestToFuncionarioMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new FuncionarioRequestToFuncionarioMapper();
    }

    @Test
    public void testMapper() {
        // Arrange
        FuncionarioRequest funcionarioRequest = new FuncionarioRequest();
        funcionarioRequest.setNome("João");
        funcionarioRequest.setIdade(30);
        funcionarioRequest.setCep("12345678");

        // Act
        Funcionario funcionario = mapper.mapper(funcionarioRequest);

        // Assert
        assertEquals("João", funcionario.getNome());
        assertEquals(30, funcionario.getIdade());
        assertEquals("12345678", funcionario.getCep());
    }
}