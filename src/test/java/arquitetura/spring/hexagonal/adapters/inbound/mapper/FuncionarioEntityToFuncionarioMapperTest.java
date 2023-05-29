package arquitetura.spring.hexagonal.adapters.inbound.mapper;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioEntityToFuncionarioMapperTest {
    @Test
    void mapper_DeveMapearFuncionarioEntityParaFuncionario() {
        // Arrange
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setId(1L);
        funcionarioEntity.setNome("João");
        funcionarioEntity.setIdade(30);
        funcionarioEntity.setCep("12345-678");
        funcionarioEntity.setBairro("Centro");
        funcionarioEntity.setCidade("São Paulo");
        funcionarioEntity.setEstado("SP");

        FuncionarioEntityToFuncionarioMapper mapper = new FuncionarioEntityToFuncionarioMapper();

        // Act
        Funcionario funcionario = mapper.mapper(funcionarioEntity);

        // Assert
        assertNotNull(funcionario);
        assertEquals(funcionarioEntity.getId(), funcionario.getId());
        assertEquals(funcionarioEntity.getNome(), funcionario.getNome());
        assertEquals(funcionarioEntity.getIdade(), funcionario.getIdade());
        assertEquals(funcionarioEntity.getCep(), funcionario.getCep());
        assertEquals(funcionarioEntity.getBairro(), funcionario.getBairro());
        assertEquals(funcionarioEntity.getCidade(), funcionario.getCidade());
        assertEquals(funcionarioEntity.getEstado(), funcionario.getEstado());
    }

    @Test
    void mapper_DeveMapearListaDeFuncionarioEntityParaListaDeFuncionario() {
        // Arrange
        List<FuncionarioEntity> funcionarioEntities = new ArrayList<>();
        FuncionarioEntity funcionarioEntity1 = new FuncionarioEntity();
        funcionarioEntity1.setId(1L);
        funcionarioEntity1.setNome("João");
        funcionarioEntity1.setIdade(30);
        funcionarioEntity1.setCep("12345-678");
        funcionarioEntity1.setBairro("Centro");
        funcionarioEntity1.setCidade("São Paulo");
        funcionarioEntity1.setEstado("SP");
        funcionarioEntities.add(funcionarioEntity1);

        FuncionarioEntity funcionarioEntity2 = new FuncionarioEntity();
        funcionarioEntity2.setId(2L);
        funcionarioEntity2.setNome("Maria");
        funcionarioEntity2.setIdade(25);
        funcionarioEntity2.setCep("98765-432");
        funcionarioEntity2.setBairro("Vila X");
        funcionarioEntity2.setCidade("Rio de Janeiro");
        funcionarioEntity2.setEstado("RJ");
        funcionarioEntities.add(funcionarioEntity2);

        FuncionarioEntityToFuncionarioMapper mapper = new FuncionarioEntityToFuncionarioMapper();

        // Act
        List<Funcionario> funcionarios = mapper.mapper(funcionarioEntities);

        // Assert
        assertNotNull(funcionarios);
        assertEquals(funcionarioEntities.size(), funcionarios.size());

        Funcionario funcionario1 = funcionarios.get(0);
        assertEquals(funcionarioEntity1.getId(), funcionario1.getId());
        assertEquals(funcionarioEntity1.getNome(), funcionario1.getNome());
        assertEquals(funcionarioEntity1.getIdade(), funcionario1.getIdade());
        assertEquals(funcionarioEntity1.getCep(), funcionario1.getCep());
        assertEquals(funcionarioEntity1.getBairro(), funcionario1.getBairro());
        assertEquals(funcionarioEntity1.getCidade(), funcionario1.getCidade());
        assertEquals(funcionarioEntity1.getEstado(), funcionario1.getEstado());
    }
}