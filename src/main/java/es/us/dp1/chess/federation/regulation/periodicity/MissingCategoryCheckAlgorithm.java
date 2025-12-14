package es.us.dp1.chess.federation.regulation.periodicity;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;

public class MissingCategoryCheckAlgorithm implements PeriodicityEventsAlgorithm{

    private List<ChessEvent> events;

    public void setExistingEvents(List<ChessEvent> events) {
        this.events = events;
    }

    @Override
    public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer periodicityDays) throws InvalidPeriodicityForANewChessEvent {

        if (events == null || events.isEmpty())
            return;

        var previousEvents = events.stream()
                .sorted(Comparator.comparing(ChessEvent::getDate))
                .toList();

        ChessEvent last = previousEvents.get(previousEvents.size() - 1);

        long days = ChronoUnit.DAYS.between(last.getDate(), newEvent.getDate());

        // Incorrect: if the days are NOT correct, do not even check the category
        if (days < periodicityDays) {
            throw new InvalidPeriodicityForANewChessEvent();
        }
    }
    
}
