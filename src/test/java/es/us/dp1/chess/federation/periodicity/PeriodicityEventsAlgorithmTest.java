package es.us.dp1.chess.federation.periodicity;

import java.time.LocalDate;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.EventCategory;
import es.us.dp1.chess.federation.regulation.Federation;
import es.us.dp1.chess.federation.regulation.periodicity.PeriodicityEventsAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.ValidAlgorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PeriodicityEventsAlgorithmTest {

    // This is your SUT. You MUST NOT modify this line.
    PeriodicityEventsAlgorithm algorithm = new ValidAlgorithm();

    // Please specify as many tests as you need
    @Test
    public void someTest() {

    }

    // This method should not be modified
    public void setAlgorithm(PeriodicityEventsAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    // We provide this method so that you can use it in your tests to create
    // ChessEvent instances easily.
    private ChessEvent createEvent(LocalDate date, EventCategory category, Integer federationId) {
        Federation f1 = new Federation();
        f1.setId(federationId);
        ChessEvent ev = new ChessEvent();
        ev.setDate(date);
        ev.setCategory(category);
        ev.setOrganizedBy(f1);
        return ev;
    }

}
