package es.us.dp1.chess.federation.regulation.periodicity;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;

public class AlwaysPeriodicityExceptionAlgorithm implements PeriodicityEventsAlgorithm{
@Override
    public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer periodicityDays) throws InvalidPeriodicityForANewChessEvent {

        throw new InvalidPeriodicityForANewChessEvent();
        
    }
}
