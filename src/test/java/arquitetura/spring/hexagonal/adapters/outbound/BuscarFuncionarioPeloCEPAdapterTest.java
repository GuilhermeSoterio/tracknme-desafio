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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarFuncionarioPeloCEPAdapterTest {

    private BuscarFuncionarioPeloCEPAdapter buscarFuncionarioPeloCEPAdapter;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarFuncionarioPeloCEPAdapter = new BuscarFuncionarioPeloCEPAdapter(funcionarioRepository, funcionarioEntityToFuncionarioMapper);
    }

    @Test
    void buscarFuncionarioCEP_DeveRetornarListaDeFuncionariosQuandoExistiremRegistros() {
        // Arrange
        String cep = "12345678";
        FuncionarioEntity funcionarioEntity1 = new FuncionarioEntity();
        FuncionarioEntity funcionarioEntity2 = new FuncionarioEntity();
        List<FuncionarioEntity> funcionarioEntityList = Arrays.asList(funcionarioEntity1, funcionarioEntity2);

        Funcionario funcionario1 = new Funcionario();
        Funcionario funcionario2 = new Funcionario();
        List<Funcionario> funcionarioList = Arrays.asList(funcionario1, funcionario2);

        when(funcionarioRepository.findByCep(cep)).thenReturn(funcionarioEntityList);
        when(funcionarioEntityToFuncionarioMapper.mapper(funcionarioEntityList)).thenReturn(funcionarioList);

        // Act
        List<Funcionario> resultado = buscarFuncionarioPeloCEPAdapter.buscarFuncionarioCEP(cep);

        // Assert
        assertEquals(funcionarioList, resultado);
    }

    @Test
    void buscarFuncionarioCEP_DeveLancarExcecaoQuandoNaoHouverRegistros() {
        // Arrange
        String cep = "12345678";

        when(funcionarioRepository.findByCep(cep)).thenReturn(Arrays.asList());

        // Act & Assert
        assertThrows(FuncionarioNotFoundException.class, () -> buscarFuncionarioPeloCEPAdapter.buscarFuncionarioCEP(cep));
    }
}