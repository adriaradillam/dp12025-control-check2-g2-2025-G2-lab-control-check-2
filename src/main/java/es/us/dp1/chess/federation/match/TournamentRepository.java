package es.us.dp1.chess.federation.match;

import java.util.Optional;

public interface TournamentRepository {

    Optional<Tournament> findById(int i);
    
}
