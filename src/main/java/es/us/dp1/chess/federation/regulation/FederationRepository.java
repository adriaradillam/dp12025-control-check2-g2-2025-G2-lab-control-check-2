package es.us.dp1.chess.federation.regulation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface FederationRepository extends CrudRepository<Federation, Integer>{

    List<Federation> findAll();

    Optional<Federation> findById(Integer id);

}
