package es.us.dp1.chess.federation.match;

import java.util.Optional;

public interface MoveRepository {

    Optional<Move> findById(int i);
    
}
