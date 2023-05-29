package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.request.FuncionarioRequest;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.core.service.EditarFuncionarioService;
import arquitetura.spring.hexagonal.application.ports.out.EditarFuncionarioPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditarFuncionarioAdapterTest {

    private EditarFuncionarioService editarFuncionarioService;

    @Mock
    private EditarFuncionarioPort editarFuncionarioPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editarFuncionarioService = new EditarFuncionarioService(editarFuncionarioPort);
    }

    @Test
    void editarFuncionario_DeveChamarEditarDoEditarFuncionarioPort() {
        // Arrange
        Long id = 1L;
        FuncionarioRequest funcionarioRequest = new FuncionarioRequest();

        Funcionario funcionarioRetornado = new Funcionario();
        when(editarFuncionarioPort.editar(id, funcionarioRequest)).thenReturn(funcionarioRetornado);

        // Act
        Funcionario resultado = editarFuncionarioService.editarFuncionario(id, funcionarioRequest);

        // Assert
        assertEquals(funcionarioRetornado, resultado);
        verify(editarFuncionarioPort).editar(id, funcionarioRequest);
    }
}