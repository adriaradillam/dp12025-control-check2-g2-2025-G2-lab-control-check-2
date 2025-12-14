package es.us.dp1.chess.federation.match;

import java.util.List;
import java.util.Optional;

public interface PieceRepository {

    List<Piece> findByColorAndTypeName(PieceColor color, String typeName);

    List<Piece> findAll();

    Optional<Piece> findById(Integer i);

    Piece save(Piece piece);
    
}
