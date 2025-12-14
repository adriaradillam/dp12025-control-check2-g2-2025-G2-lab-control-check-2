package es.us.dp1.chess.federation.regulation;

import java.util.List;
import java.util.Optional;

public interface RefereeRepository {

    List<Referee> findAll();

    Optional<Referee> findById(Integer id);

}
