package arquitetura.spring.hexagonal.adapters.inbound.mapper;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioToFuncionarioEntityMapperTest {
    private FuncionarioToFuncionarioEntityMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new FuncionarioToFuncionarioEntityMapper();
    }

    @Test
    public void testMapper() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setIdade(30);
        funcionario.setCep("12345678");

        // Act
        FuncionarioEntity funcionarioEntity = mapper.mapper(funcionario);

        // Assert
        assertEquals("João", funcionarioEntity.getNome());
        assertEquals(30, funcionarioEntity.getIdade());
        assertEquals("12345678", funcionarioEntity.getCep());
    }
}