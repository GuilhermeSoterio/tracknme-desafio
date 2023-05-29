package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.exception.FuncionarioNotFoundException;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExcluirFuncionarioAdapterTest {

    private ExcluirFuncionarioAdapter excluirFuncionarioAdapter;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        excluirFuncionarioAdapter = new ExcluirFuncionarioAdapter(funcionarioRepository, funcionarioEntityToFuncionarioMapper);
    }

    @Test
    void testExcluirFuncionario() {
        Long funcionarioId = 1L;
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setId(funcionarioId);

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionarioEntity));

        Funcionario funcionarioResponse = new Funcionario();
        when(funcionarioEntityToFuncionarioMapper.mapper(funcionarioEntity)).thenReturn(funcionarioResponse);

        Funcionario result = excluirFuncionarioAdapter.excluirFuncionario(funcionarioId);

        assertEquals(funcionarioResponse, result);
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(funcionarioRepository, times(1)).deleteById(funcionarioId);
        verify(funcionarioEntityToFuncionarioMapper, times(1)).mapper(funcionarioEntity);
    }

    @Test
    void testExcluirFuncionarioNotFound() {
        Long funcionarioId = 1L;

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.empty());

        assertThrows(FuncionarioNotFoundException.class, () -> {
            excluirFuncionarioAdapter.excluirFuncionario(funcionarioId);
        });

        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(funcionarioRepository, never()).deleteById(funcionarioId);
        verify(funcionarioEntityToFuncionarioMapper, never()).mapper((FuncionarioEntity) any());
    }

}