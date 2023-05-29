package arquitetura.spring.hexagonal.adapters.inbound.utils;

import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioUtilsTest {
    @Test
    void precisaAtualizarEndereco_DeveRetornarFalseQuandoFuncionarioTemEnderecoCompleto() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setCep("12345-678");
        funcionario.setBairro("Centro");
        funcionario.setCidade("São Paulo");
        funcionario.setEstado("SP");

        // Act
        boolean result = FuncionarioUtils.precisaAtualizarEndereco(funcionario);

        // Assert
        assertFalse(result);
    }

    @Test
    void precisaAtualizarEndereco_DeveRetornarTrueQuandoFuncionarioTemEnderecoIncompleto() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setCep("12345-678");
        funcionario.setBairro("Centro");
        funcionario.setCidade(null);
        funcionario.setEstado("SP");

        // Act
        boolean result = FuncionarioUtils.precisaAtualizarEndereco(funcionario);

        // Assert
        assertTrue(result);
    }

    @Test
    void precisaAtualizarEndereco_DeveRetornarTrueQuandoFuncionarioTemCepMasEnderecoNaoInformado() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setCep("12345-678");
        funcionario.setBairro(null);
        funcionario.setCidade(null);
        funcionario.setEstado(null);

        // Act
        boolean result = FuncionarioUtils.precisaAtualizarEndereco(funcionario);

        // Assert
        assertTrue(result);
    }
}