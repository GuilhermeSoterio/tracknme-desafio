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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditarFuncionarioParcialmenteAdapterTest {
    private EditarFuncionarioParcialmenteAdapter editarFuncionarioAdapter;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editarFuncionarioAdapter = new EditarFuncionarioParcialmenteAdapter(funcionarioRepository, funcionarioEntityToFuncionarioMapper);
    }

    @Test
    void editar_DeveAtualizarFuncionarioComCamposParciais() {
        // Arrange
        Long id = 1L;
        Map<String, Object> camposParciais = new HashMap<>();
        camposParciais.put("nome", "Novo Nome");
        camposParciais.put("idade", 30);
        camposParciais.put("cidade", "Nova Cidade");

        FuncionarioEntity funcionarioExistente = new FuncionarioEntity();
        funcionarioExistente.setId(id);
        funcionarioExistente.setNome("Nome Antigo");
        funcionarioExistente.setIdade(25);
        funcionarioExistente.setCidade("Cidade Antiga");

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionarioExistente));
        when(funcionarioEntityToFuncionarioMapper.mapper(funcionarioExistente)).thenReturn(new Funcionario());

        // Act
        Funcionario resultado = editarFuncionarioAdapter.editar(id, camposParciais);

        // Assert
        assertEquals("Novo Nome", funcionarioExistente.getNome());
        assertEquals(30, funcionarioExistente.getIdade());
        assertEquals("Nova Cidade", funcionarioExistente.getCidade());
        verify(funcionarioRepository, times(1)).save(funcionarioExistente);
        verify(funcionarioEntityToFuncionarioMapper, times(1)).mapper(funcionarioExistente);
        assertEquals(Funcionario.class, resultado.getClass());
    }

    @Test
    void editar_DeveRetornarExcecaoQuandoFuncionarioNaoExiste() {
        // Arrange
        Long id = 1L;
        Map<String, Object> camposParciais = new HashMap<>();

        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        FuncionarioNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                FuncionarioNotFoundException.class,
                () -> editarFuncionarioAdapter.editar(id, camposParciais)
        );

        assertEquals("Funcionario com ID " + id + " não encontrado.", exception.getMessage());
        verify(funcionarioRepository, times(0)).save(any());
        verify(funcionarioEntityToFuncionarioMapper, times(0)).mapper((FuncionarioEntity) any());
    }
}