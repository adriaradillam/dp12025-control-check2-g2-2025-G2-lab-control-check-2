package es.us.dp1.chess.federation.regulation.periodicity;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;

public interface PeriodicityEventsAlgorithm {

    public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer minDays)
            throws InvalidPeriodicityForANewChessEvent;

}
