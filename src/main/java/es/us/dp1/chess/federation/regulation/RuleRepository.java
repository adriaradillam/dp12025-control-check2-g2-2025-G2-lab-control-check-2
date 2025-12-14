package es.us.dp1.chess.federation.regulation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    Optional<Rule> findById(Integer id);

    List<Rule> findAll();
   

}
