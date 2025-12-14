package es.us.dp1.chess.federation.regulation;

import java.util.List;
import java.util.Optional;

public interface SanctionRepository {

    List<Sanction> findAll();

    Optional<Sanction> findById(Integer id);


}
