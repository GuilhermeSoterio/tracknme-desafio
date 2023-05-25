package arquitetura.spring.hexagonal.config;

import arquitetura.spring.hexagonal.adapters.outbound.*;
import arquitetura.spring.hexagonal.application.core.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
	public SalvarFuncionarioService salvarUsuarioService(SalvarFuncionarioAdapter salvarUsuarioAdapter,
														 BuscarEnderecoAdapter buscarEnderecoAdapter){
		return new SalvarFuncionarioService(salvarUsuarioAdapter, buscarEnderecoAdapter);
	}

	@Bean
	public BuscarFuncionarioPeloIdService buscarFuncionarioPeloIdService(BuscarFuncionarioPeloIdAdapter buscarFuncionarioPeloIdAdapter){
		return new BuscarFuncionarioPeloIdService(buscarFuncionarioPeloIdAdapter);
	}

	@Bean
	public BuscarTodosFuncionariosService buscarTodosFuncionariosService(BuscarTodosFuncionariosAdapter buscarTodosFuncionariosAdapter){
		return new BuscarTodosFuncionariosService(buscarTodosFuncionariosAdapter);
	}

	@Bean
	public BuscarFuncionariosPeloCEPService buscarFuncionariosPeloCEPService(BuscarFuncionarioPeloCEPAdapter buscarFuncionarioPeloCEPAdapter){
		return new BuscarFuncionariosPeloCEPService(buscarFuncionarioPeloCEPAdapter);
	}

	@Bean
	public EditarFuncionarioService editarFuncionarioService(EditarFuncionarioAdapter editarFuncionarioAdapter){
		return new EditarFuncionarioService(editarFuncionarioAdapter);
	}
}
