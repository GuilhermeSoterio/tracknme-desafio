package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.outbound.rest.BuscarEnderecoRest;
import arquitetura.spring.hexagonal.application.core.domain.Endereco;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarEnderecoAdapterTest {

    private BuscarEnderecoAdapter buscarEnderecoAdapter;

    @Mock
    private BuscarEnderecoRest buscarEnderecoRest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarEnderecoAdapter = new BuscarEnderecoAdapter(buscarEnderecoRest);
    }

    @Test
    void buscar_DeveRetornarEnderecoQuandoRequisicaoBemSucedida() {
        // Arrange
        String cep = "12345678";
        Endereco enderecoRetornado = new Endereco();
        ResponseEntity<Endereco> responseEntity = ResponseEntity.ok(enderecoRetornado);

        when(buscarEnderecoRest.buscar(cep)).thenReturn(responseEntity);

        // Act
        Endereco resultado = buscarEnderecoAdapter.buscar(cep);

        // Assert
        assertEquals(enderecoRetornado, resultado);
    }

    @Test
    void buscar_DeveLancarExcecaoQuandoRequisicaoComCepInvalido() {
        // Arrange
        String cep = "123";
        ResponseEntity<Endereco> responseEntity = ResponseEntity.notFound().build();

        when(buscarEnderecoRest.buscar(cep)).thenReturn(responseEntity);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> buscarEnderecoAdapter.buscar(cep));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Erro ao buscar o CEP.", exception.getReason());
    }
}