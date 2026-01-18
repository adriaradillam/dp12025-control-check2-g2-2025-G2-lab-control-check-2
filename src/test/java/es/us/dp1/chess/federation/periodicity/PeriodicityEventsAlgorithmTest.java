package es.us.dp1.chess.federation.periodicity;

import java.time.LocalDate;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.EventCategory;
import es.us.dp1.chess.federation.regulation.Federation;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;
import es.us.dp1.chess.federation.regulation.periodicity.PeriodicityEventsAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.ValidAlgorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PeriodicityEventsAlgorithmTest {

    // This is your SUT. You MUST NOT modify this line.
    PeriodicityEventsAlgorithm algorithm = new ValidAlgorithm();

    // Test 1: Misma categoría, misma federación, días <= periodicityDays (4 <= 10)
    // DEBE lanzar excepción
    @Test
    public void testShouldThrowExceptionWhenSameCategoryAndFederationWithInsufficientDays() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 5), EventCategory.WORLD, 1);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 1);
        Assertions.assertThrows(InvalidPeriodicityForANewChessEvent.class,
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 10));
    }

    // Test 2: Misma categoría, misma federación, días > periodicityDays (4 > 1)
    // NO debe lanzar excepción
    @Test
    public void testShouldNotThrowExceptionWhenSameCategoryAndFederationWithSufficientDays() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 3), EventCategory.WORLD, 1);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 1);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 1));
    }

    // Test 3: Misma categoría, misma federación, días > periodicityDays (4 > 3)
    // NO debe lanzar excepción
    @Test
    public void testShouldNotThrowExceptionWhenDaysStrictlyGreaterThanPeriodicity() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 5), EventCategory.WORLD, 1);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 1);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 3));
    }

    // Test 4: Diferentes categorías (WORLD vs NATIONAL), misma federación
    // NO debe lanzar excepción (diferentes categorías)
    @Test
    public void testShouldNotThrowExceptionWhenDifferentCategories() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 5), EventCategory.NATIONAL, 1);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 1);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 10));
    }

    // Test 5: Misma categoría, diferentes federaciones
    // NO debe lanzar excepción (diferentes federaciones)
    @Test
    public void testShouldNotThrowExceptionWhenDifferentFederations() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 5), EventCategory.WORLD, 1);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 2);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 10));
    }

    // Test 6: Misma categoría, misma federación, días = periodicityDays
    // (exactamente igual)
    // DEBE lanzar excepción (4 días = 4 periodicidad, no es estrictamente mayor)
    @Test
    public void testShouldThrowExceptionWhenDaysEqualToPeriodicity() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 5), EventCategory.REGIONAL, 3);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.REGIONAL, 3);
        Assertions.assertThrows(InvalidPeriodicityForANewChessEvent.class,
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 4));
    }

    // Test 7: Evento anterior después del nuevo (fecha invertida)
    // DEBE lanzar excepción (diferencia negativa de días)
    @Test
    public void testShouldThrowExceptionWhenPreviousEventIsAfterNewEvent() {
        ChessEvent event = createEvent(LocalDate.of(2025, 11, 5), EventCategory.WORLD, 1);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 1);
        Assertions.assertThrows(InvalidPeriodicityForANewChessEvent.class,
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 1));
    }

    // Test 8: Diferencia grande de días pero periodicidad muy alta
    // NO debe lanzar excepción (30 días > 20 periodicidad)
    @Test
    public void testShouldNotThrowExceptionWithLargeDayDifference() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 31), EventCategory.WORLD, 5);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.WORLD, 5);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 20));
    }

    // Test 9: Categoría REGIONAL, diferentes federaciones
    // NO debe lanzar excepción (diferentes federaciones)
    @Test
    public void testShouldNotThrowExceptionWithRegionalCategoryDifferentFederations() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 3), EventCategory.REGIONAL, 2);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.REGIONAL, 3);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 100));
    }

    // Test 10: Periodicidad de 0 días, eventos con 1 día de diferencia
    // NO debe lanzar excepción (1 > 0)
    @Test
    public void testShouldNotThrowExceptionWithZeroPeriodicity() {
        ChessEvent event = createEvent(LocalDate.of(2025, 12, 2), EventCategory.NATIONAL, 4);
        ChessEvent previousEvent = createEvent(LocalDate.of(2025, 12, 1), EventCategory.NATIONAL, 4);
        Assertions.assertDoesNotThrow(
                () -> algorithm.validatePeriodicityEvents(event, previousEvent, 0));
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