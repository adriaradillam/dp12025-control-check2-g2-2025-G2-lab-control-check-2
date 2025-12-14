package es.us.dp1.chess.federation.regulation.periodicity;

import java.time.temporal.ChronoUnit;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;

public class ValidAlgorithm implements PeriodicityEventsAlgorithm {

    @Override
    public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer minDays)
            throws InvalidPeriodicityForANewChessEvent {

        if (newEvent == null || newEvent.getOrganizedBy() == null) {
            throw new InvalidPeriodicityForANewChessEvent("Invalid event or federation.");
        }

        // There is no previous event. Registration is correct.
        if (previousEvent == null) {
            System.out.println("PREVIOUS EVENT IS NULL  - ALLOWED");
            return;
        }

        // Validate only if both events are organized by the same federation
        Integer newFed = newEvent.getOrganizedBy().getId();
        Integer prevFed = previousEvent.getOrganizedBy().getId();
        if (!newFed.equals(prevFed)) {
            // Un evento previo de otra federación no debe bloquear
            return;
        }

        // If the difference is GREATER → valid
        long daysBetween = ChronoUnit.DAYS.between(previousEvent.getDate(), newEvent.getDate());
        if (daysBetween > minDays) {
            return;
        }

        if (previousEvent.getCategory() != null
                && newEvent.getCategory() != null
                && !previousEvent.getCategory().equals(newEvent.getCategory())) {

            // Different categories → allowed
            return;
        }

        // Same category → NOT allowed
        throw new InvalidPeriodicityForANewChessEvent(
                "The new event does not satisfy minimum periodicity requirements.");
    }
}
