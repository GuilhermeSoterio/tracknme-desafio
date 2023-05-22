package arquitetura.spring.hexagonal.adapters.outbound.repository;

import arquitetura.spring.hexagonal.adapters.inbound.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    List<FuncionarioEntity> findByCep(String cep);
}
