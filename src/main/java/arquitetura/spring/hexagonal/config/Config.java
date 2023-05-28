package arquitetura.spring.hexagonal.config;

import arquitetura.spring.hexagonal.adapters.outbound.*;
import arquitetura.spring.hexagonal.application.core.service.*;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = Arrays.asList(
                new ConcurrentMapCache("Funcionarios"),
                new ConcurrentMapCache("FuncionariosPorCEP"),
                new ConcurrentMapCache("FuncionariosPorId")
        );
        cacheManager.setCaches(caches);
        return cacheManager;
    }
    @Bean
    public SalvarFuncionarioService salvarUsuarioService(
            SalvarFuncionarioAdapter salvarUsuarioAdapter,
            BuscarEnderecoAdapter buscarEnderecoAdapter) {
        return new SalvarFuncionarioService(salvarUsuarioAdapter, buscarEnderecoAdapter);
    }

    @Bean
    public BuscarFuncionarioPeloIdService buscarFuncionarioPeloIdService(
            BuscarFuncionarioPeloIdAdapter buscarFuncionarioPeloIdAdapter) {
        return new BuscarFuncionarioPeloIdService(buscarFuncionarioPeloIdAdapter);
    }

    @Bean
    public BuscarTodosFuncionariosService buscarTodosFuncionariosService(
            BuscarTodosFuncionariosAdapter buscarTodosFuncionariosAdapter) {
        return new BuscarTodosFuncionariosService(buscarTodosFuncionariosAdapter);
    }

    @Bean
    public BuscarFuncionariosPeloCEPService buscarFuncionariosPeloCEPService(
            BuscarFuncionarioPeloCEPAdapter buscarFuncionarioPeloCEPAdapter) {
        return new BuscarFuncionariosPeloCEPService(buscarFuncionarioPeloCEPAdapter);
    }

    @Bean
    public EditarFuncionarioService editarFuncionarioService(
            EditarFuncionarioAdapter editarFuncionarioAdapter) {
        return new EditarFuncionarioService(editarFuncionarioAdapter);
    }

    @Bean
    public EditarParcialmenteFuncionarioService editarParcialmenteFuncionarioService(
            EditarFuncionarioParcialmenteAdapter editarFuncionarioParcialmenteAdapter,
            BuscarEnderecoAdapter buscarEnderecoAdapter) {
        return new EditarParcialmenteFuncionarioService(editarFuncionarioParcialmenteAdapter, buscarEnderecoAdapter);
    }
}
