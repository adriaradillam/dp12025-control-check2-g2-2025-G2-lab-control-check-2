package es.us.dp1.chess.federation.match;

import java.time.LocalDateTime;

import es.us.dp1.chess.federation.model.NamedEntity;
import es.us.dp1.chess.federation.user.User;

import java.lang.Cloneable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChessMatch extends NamedEntity implements Cloneable {
    LocalDateTime start;
    LocalDateTime finish;
    Long turnDuration;
    

    @ManyToOne
    User whitePlayer;

    @ManyToOne
    User blackPlayer;

    @ManyToOne
    User winner;

    @OneToOne(cascade = CascadeType.ALL)
    ChessBoard board;

    public ChessMatch clone() {
        ChessMatch match = new ChessMatch();
        match.setName(this.getName());
        match.setStart(this.getStart());
        match.setFinish(this.getFinish());
        match.setTurnDuration(this.getTurnDuration());     
        match.setBlackPlayer(this.getBlackPlayer());
        match.setWhitePlayer(this.getWhitePlayer());
        match.setWinner(this.getWinner());
        match.setBoard(getBoard().clone());
        return match;
    }

}
