package es.us.dp1.chess.federation.match;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.us.dp1.chess.federation.model.BaseEntity;

import java.lang.Cloneable;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Piece extends BaseEntity implements Cloneable{
    

    @OneToOne
    Position position;

    @ManyToOne()
    @JsonIgnore
    ChessBoard board;

    @ManyToOne()    
    private PieceType type;

    @Enumerated(EnumType.STRING)
    PieceColor color;

    Boolean captured;

    public Piece clone() {
        Piece piece = new Piece();
        piece.setPosition(this.getPosition());
        //piece.setYPosition(this.getYPosition());
        piece.setType(this.getType());
        piece.setColor(this.getColor());
        return piece;
    }


}
