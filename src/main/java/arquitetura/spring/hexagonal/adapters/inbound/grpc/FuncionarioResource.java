package arquitetura.spring.hexagonal.adapters.inbound.grpc;

import arquitetura.spring.hexagonal.adapters.inbound.dto.FuncionarioInputDTO;
import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioRequestToFuncionarioMapper;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioToFuncionarioEntityMapper;
import arquitetura.spring.hexagonal.adapters.outbound.repository.FuncionarioRepository;
import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import arquitetura.spring.hexagonal.application.ports.in.SalvarFuncionarioServicePort;
import br.com.content4devs.FuncionarioRequest;
import br.com.content4devs.FuncionarioResponse;
import br.com.content4devs.FuncionarioServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FuncionarioResource extends FuncionarioServiceGrpc.FuncionarioServiceImplBase {

    private final SalvarFuncionarioServicePort salvarUsuarioServicePort;

    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    public FuncionarioResource( SalvarFuncionarioServicePort salvarUsuarioServicePort,
                                FuncionarioEntityToFuncionarioMapper funcionarioEntityToUsuarioMapper) {
        this.salvarUsuarioServicePort = salvarUsuarioServicePort;
        this.funcionarioEntityToFuncionarioMapper = funcionarioEntityToUsuarioMapper;
    }

    @Override
    public void create(FuncionarioRequest request, StreamObserver<FuncionarioResponse> responseObserver) {
        Sexo sexo = (request.getSexo() == 1) ? Sexo.MASCULINO : Sexo.FEMININO;

        var funcionarioEntity = new FuncionarioEntity(
                request.getName(),
                request.getBairro(),
                request.getIdade(),
                request.getCep(),
                sexo,
                request.getCidade(),
                request.getEstado()
        );

        var funcionarioResponse = salvarUsuarioServicePort.salvarUsuario(funcionarioEntityToFuncionarioMapper.mapper(funcionarioEntity), funcionarioEntity.getCep());
        responseObserver.onNext(FuncionarioResponse.newBuilder()
                .setBairro(funcionarioResponse.getBairro())
                .setIdade(funcionarioResponse.getIdade())
                .setCep(funcionarioResponse.getCep())
                .setSexo(1)
                .setBairro(funcionarioResponse.getBairro())
                .setCidade(funcionarioResponse.getCidade())
                .setEstado(funcionarioResponse.getEstado())
                .build());
        responseObserver.onCompleted();
    }

    public void buscarPorId(){

    }

}
