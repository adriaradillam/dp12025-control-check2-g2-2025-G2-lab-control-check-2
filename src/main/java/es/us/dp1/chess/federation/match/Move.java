package es.us.dp1.chess.federation.match;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
    Integer turn;
    Integer originX;
    Integer originY;
    Integer destinationX;
    Integer destinationY;

    @Transient
    ChessMatch match;
    @Transient
    Piece piece;
    @Transient
    Piece capture;
}
