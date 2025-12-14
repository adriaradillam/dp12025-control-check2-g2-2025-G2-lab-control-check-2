package es.us.dp1.chess.federation.match;

import java.util.List;
import java.util.Optional;

public class PieceService {

    PieceRepository repository;

    public PieceService(Optional<PieceRepository> repository) {
        this.repository = repository.isPresent()?repository.get():null;
    }

    public List<Piece> getPiecesByColorAndType(PieceColor color, String typeName) {
        // TODO: implement this method to solve Exercise 2a
        return null;
    }

    public List<Piece> getAll() {
        // TODO: implement this method to solve Exercise 2a
        return null;
    }

    public Piece getById(Integer i) {
        // TODO: implement this method to solve Exercise 2a
        return null;
    }

    public Piece save(Piece piece) {
        // TODO: implement this method to solve Exercise 2a
        return null;
    }
    
}
