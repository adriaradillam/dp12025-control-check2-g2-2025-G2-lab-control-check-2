package es.us.dp1.chess.federation.regulation;


import es.us.dp1.chess.federation.user.User;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sanction{

    String description;

    Double monetaryFine;

    SanctionType type;

    @Transient
    Referee imposedBy;

    @Transient
    User imposedOn;

    @Transient
    Rule ruleBroken;
    

}
