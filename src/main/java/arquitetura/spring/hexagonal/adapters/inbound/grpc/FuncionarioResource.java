package arquitetura.spring.hexagonal.adapters.inbound.grpc;

import br.com.content4devs.FuncionarioServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FuncionarioResource extends FuncionarioServiceGrpc.FuncionarioServiceImplBase {
}
