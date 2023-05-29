package arquitetura.spring.hexagonal.adapters.outbound;

import arquitetura.spring.hexagonal.adapters.outbound.rest.BuscarEnderecoRest;
import arquitetura.spring.hexagonal.application.core.domain.Endereco;
import arquitetura.spring.hexagonal.application.ports.out.BuscarEnderecoPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class BuscarEnderecoAdapter implements BuscarEnderecoPort {

    private final BuscarEnderecoRest buscarEnderecoRest;


    @Override
    public Endereco buscar(String cep) {
        try {
            var resposta = buscarEnderecoRest.buscar(cep);
            var response = resposta.getBody();

            if (response == null) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido. Por favor, verifique o CEP informado.");
            }

            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar o CEP.");
        }
    }
}
