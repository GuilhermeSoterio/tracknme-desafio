package arquitetura.spring.hexagonal.adapters.inbound.grpc;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import arquitetura.spring.hexagonal.adapters.inbound.mapper.FuncionarioEntityToFuncionarioMapper;
import arquitetura.spring.hexagonal.application.core.domain.Funcionario;
import arquitetura.spring.hexagonal.application.core.domain.enums.Sexo;
import arquitetura.spring.hexagonal.application.ports.in.BuscarFuncionarioPeloIdServicePort;
import arquitetura.spring.hexagonal.application.ports.in.SalvarFuncionarioServicePort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarFuncionarioPeloCEPPort;
import arquitetura.spring.hexagonal.application.ports.out.BuscarTodosFuncionariosPort;
import br.com.content4devs.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class FuncionarioResource extends FuncionarioServiceGrpc.FuncionarioServiceImplBase {

    private final SalvarFuncionarioServicePort salvarUsuarioServicePort;

    private final FuncionarioEntityToFuncionarioMapper funcionarioEntityToFuncionarioMapper;

    private final BuscarFuncionarioPeloIdServicePort buscarFuncionarioPeloIdServicePort;

    private final BuscarFuncionarioPeloCEPPort buscarFuncionarioPeloCEPPort;

    private final BuscarTodosFuncionariosPort buscarTodosFuncionariosPort;

    public FuncionarioResource(SalvarFuncionarioServicePort salvarUsuarioServicePort,
                               FuncionarioEntityToFuncionarioMapper funcionarioEntityToUsuarioMapper, BuscarFuncionarioPeloIdServicePort buscarFuncionarioPeloIdServicePort, BuscarFuncionarioPeloCEPPort buscarFuncionarioPeloCEPPort, BuscarTodosFuncionariosPort buscarTodosFuncionariosPort) {
        this.salvarUsuarioServicePort = salvarUsuarioServicePort;
        this.funcionarioEntityToFuncionarioMapper = funcionarioEntityToUsuarioMapper;
        this.buscarFuncionarioPeloIdServicePort = buscarFuncionarioPeloIdServicePort;
        this.buscarFuncionarioPeloCEPPort = buscarFuncionarioPeloCEPPort;
        this.buscarTodosFuncionariosPort = buscarTodosFuncionariosPort;
    }

    @Override
    public void create(FuncionarioRequest request, StreamObserver<FuncionarioResponse> responseObserver) {

            Sexo sexo = (request.getSexo() == 1) ? Sexo.MASCULINO : Sexo.FEMININO;

        var funcionarioEntity = new FuncionarioEntity(
                request.getNome(),
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
                .setSexo(funcionarioResponse.getSexo().getDescricao())
                .setBairro(funcionarioResponse.getBairro())
                .setCidade(funcionarioResponse.getCidade())
                .setEstado(funcionarioResponse.getEstado())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void buscarPorId(buscarPorIdRequest request, StreamObserver<FuncionarioResponse> responseObserver) {
        var funcionarioResponse = buscarFuncionarioPeloIdServicePort.buscarPeloId(request.getId());
        responseObserver.onNext(FuncionarioResponse.newBuilder()
                .setBairro(funcionarioResponse.getBairro())
                .setIdade(funcionarioResponse.getIdade())
                .setCep(funcionarioResponse.getCep())
                .setSexo(funcionarioResponse.getSexo().getDescricao())
                .setBairro(funcionarioResponse.getBairro())
                .setCidade(funcionarioResponse.getCidade())
                .setEstado(funcionarioResponse.getEstado())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void buscarPorCep(buscarPorCepRequest request, StreamObserver<FuncionarioResponseList> responseObserver) {
        List<Funcionario> funcionariosList = buscarFuncionarioPeloCEPPort.buscarFuncionarioCEP(request.getCep());
        List<FuncionarioResponse>  funcionarioResponseList = funcionariosList.stream()
                        .map(funcionario ->
                            FuncionarioResponse.newBuilder()
                                    .setBairro(funcionario.getBairro())
                                    .setIdade(funcionario.getIdade())
                                    .setCep(funcionario.getCep())
                                    .setSexo(funcionario.getSexo().getDescricao())
                                    .setBairro(funcionario.getBairro())
                                    .setCidade(funcionario.getCidade())
                                    .setEstado(funcionario.getEstado())
                                    .build())
                            .collect(Collectors.toList());

        FuncionarioResponseList response = FuncionarioResponseList.newBuilder()
                .addAllFuncionarios(funcionarioResponseList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void buscarTodos(EmptyRequest request, StreamObserver<FuncionarioResponseList> responseObserver) {
        List<Funcionario> funcionariosList = buscarTodosFuncionariosPort.buscarTodos();
        List<FuncionarioResponse>  funcionarioResponseList = funcionariosList.stream()
                .map(funcionario ->
                        FuncionarioResponse.newBuilder()
                                .setBairro(funcionario.getBairro())
                                .setIdade(funcionario.getIdade())
                                .setCep(funcionario.getCep())
                                .setSexo(funcionario.getSexo().getDescricao())
                                .setBairro(funcionario.getBairro())
                                .setCidade(funcionario.getCidade())
                                .setEstado(funcionario.getEstado())
                                .build())
                .collect(Collectors.toList());

        FuncionarioResponseList response = FuncionarioResponseList.newBuilder()
                .addAllFuncionarios(funcionarioResponseList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
