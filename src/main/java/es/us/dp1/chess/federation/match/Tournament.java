package es.us.dp1.chess.federation.match;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tournament {
    String name;
    String location;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer maxParticipants;

    @Transient
    List<ChessMatch> matches;
}
