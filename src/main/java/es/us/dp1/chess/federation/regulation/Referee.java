package es.us.dp1.chess.federation.regulation;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Referee{

    String licenseNumber;

    LocalDate certificationDate;
    
    @Transient
    Federation certifiedBy; 

    @Transient
    List<ChessEvent> assignedTo; 

}