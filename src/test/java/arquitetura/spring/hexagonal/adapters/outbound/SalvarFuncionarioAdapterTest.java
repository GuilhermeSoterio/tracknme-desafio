package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioToFuncionarioEntityMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalvarFuncionarioAdapterTest {
    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioToFuncionarioEntityMapper funcionarioToFuncionarioEntityMapper;

    @Mock
    private FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    @InjectMocks
    private SalvarFuncionarioAdapter salvarFuncionarioAdapter;

    @Test
    public void testSalvar() {
        // Dados de entrada
        Funcionario funcionario = new Funcionario();
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

        // Configurar o comportamento dos mocks
        when(funcionarioToFuncionarioEntityMapper.mapper(funcionario)).thenReturn(funcionarioEntity);
        when(funcionarioRepository.save(funcionarioEntity)).thenReturn(funcionarioEntity);
        when(funcionarioEntityToFuncionarioMapper.mapper(funcionarioEntity)).thenReturn(funcionario);

        // Executar o método a ser testado
        Funcionario resultado = salvarFuncionarioAdapter.salvar(funcionario);

        // Verificar se os métodos dos mocks foram chamados corretamente
        verify(funcionarioToFuncionarioEntityMapper).mapper(funcionario);
        verify(funcionarioRepository).save(funcionarioEntity);
        verify(funcionarioEntityToFuncionarioMapper).mapper(funcionarioEntity);

        // Verificar o resultado do método
        assertEquals(funcionario, resultado);
    }

}