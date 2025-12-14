package es.us.dp1.chess.federation.regulation.periodicity;

import es.us.dp1.chess.federation.regulation.ChessEvent;

public class DoNothingAlgorithm implements PeriodicityEventsAlgorithm{
    @Override
    public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer periodicityDays) {
        
    }
}
