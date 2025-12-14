package es.us.dp1.chess.federation.regulation.periodicity;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;

public class WrongDaysCalculationAlgorithm implements PeriodicityEventsAlgorithm{

    private List<ChessEvent> events;

    public void setExistingEvents(List<ChessEvent> events) {
        this.events = events;
    }

    @Override
    public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer periodicityDays) throws InvalidPeriodicityForANewChessEvent {

        if (events == null || events.isEmpty())
            return;

        ChessEvent last = events.stream()
                .sorted(Comparator.comparing(ChessEvent::getDate))
                .toList()
                .get(0); // incorrecto: usa el evento más antiguo

        long days = ChronoUnit.DAYS.between(newEvent.getDate(), last.getDate()); // invertido

        if (days < periodicityDays)
            throw new InvalidPeriodicityForANewChessEvent();
    }
}
