package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarFuncionarioPeloIdAdapterTest {

    private BuscarFuncionarioPeloIdAdapter buscarFuncionarioPeloIdAdapter;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarFuncionarioPeloIdAdapter = new BuscarFuncionarioPeloIdAdapter(funcionarioRepository, funcionarioEntityToFuncionarioMapper);
    }

    @Test
    void buscarPeloId_DeveRetornarFuncionarioQuandoExistir() {
        // Arrange
        Long id = 1L;
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        Funcionario funcionario = new Funcionario();

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionarioEntity));
        when(funcionarioEntityToFuncionarioMapper.mapper(funcionarioEntity)).thenReturn(funcionario);

        // Act
        Funcionario resultado = buscarFuncionarioPeloIdAdapter.BuscarPeloId(id);

        // Assert
        assertEquals(funcionario, resultado);
    }

    @Test
    void buscarPeloId_DeveLancarExcecaoQuandoNaoExistir() {
        // Arrange
        Long id = 1L;

        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FuncionarioNotFoundException.class, () -> buscarFuncionarioPeloIdAdapter.BuscarPeloId(id));
    }
}