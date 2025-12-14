package es.us.dp1.chess.federation;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import es.us.dp1.chess.federation.periodicity.PeriodicityEventsAlgorithmTest;
import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.InvalidPeriodicityForANewChessEvent;
import es.us.dp1.chess.federation.regulation.periodicity.AlwaysPeriodicityExceptionAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.DoNothingAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.MissingCategoryCheckAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.MissingFederationsCheckAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.PeriodicityEventsAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.ValidAlgorithm;
import es.us.dp1.chess.federation.regulation.periodicity.WrongDaysCalculationAlgorithm;

public class Test5 extends ReflexiveTest {

    public class WrapperAlgorithm implements PeriodicityEventsAlgorithm {

        private final PeriodicityEventsAlgorithm algorithm;
        private int numRuns;

        public WrapperAlgorithm(PeriodicityEventsAlgorithm algorithm) {
            this.algorithm = algorithm;
            this.numRuns = 0;
        }

        @Override
        public void validatePeriodicityEvents(ChessEvent newEvent, ChessEvent previousEvent, Integer periodicityDays)
                throws InvalidPeriodicityForANewChessEvent {
            numRuns++;
            algorithm.validatePeriodicityEvents(newEvent, previousEvent, periodicityDays);
        }

        public int getNumRuns() {
            return numRuns;
        }
    }

    @ParameterizedTest
    @MethodSource("provideAlgorithmsAndExpectedResults")
    public void test5PeriodicityAlgorithm(PeriodicityEventsAlgorithm alg, boolean shouldFail) {
        PeriodicityEventsAlgorithmTest algoTest = new PeriodicityEventsAlgorithmTest();
        WrapperAlgorithm wrapper = new WrapperAlgorithm(alg);
        algoTest.setAlgorithm(wrapper);
        int executedTests = 0;

        executedTests = executeTests(algoTest, shouldFail, alg);

        if (executedTests < 1) {
            fail("You have not specified any test method or have used the wrong annotation!");
        }
        if (wrapper.getNumRuns() < 1) {
            fail("The algorithm has not been executed in the test!");
        }
    }

    private int executeTests(PeriodicityEventsAlgorithmTest algoTest, boolean shouldFail,
            PeriodicityEventsAlgorithm alg) {
        int executed = 0;
        Method[] methods = algoTest.getClass().getDeclaredMethods();
        boolean failDetected = false;
        String message = "Test methods could not detect the faulty implementation of the algorithm in class "
                + alg.getClass().getSimpleName();

        for (Method method : methods) {
            if (isMethodAnnotatedWithTest(method)) {
                try {
                    executed++;
                    executeBeforeEach(algoTest);
                    method.invoke(algoTest);
                    executeAfterEach(algoTest);
                } catch (AssertionError assertionError) {
                    failDetected = true;
                    message = "The test method named " + method.getName() + " failed (and should not)! AssertionError: "
                            + assertionError.getMessage();
                } catch (InvocationTargetException e) {
                    if (e.getTargetException() instanceof org.opentest4j.AssertionFailedError) {
                        failDetected = true;
                        message = "The test method named " + method.getName()
                                + " failed (and should not)! AssertionError: "
                                + ((org.opentest4j.AssertionFailedError) e.getTargetException()).getMessage();
                    } else {
                        System.out.println("Error while trying to invoke method: " + method.getName());
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    System.out.println("Error while trying to invoke method: " + method.getName());
                }
            }
        }

        if (failDetected != shouldFail)
            fail(message);

        return executed;
    }

    private void executeBeforeEach(PeriodicityEventsAlgorithmTest algoTest) {
        Method[] methods = algoTest.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (isMethodAnnotatedWithBeforeEach(method)) {
                try {
                    method.invoke(algoTest);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error while trying to invoke method:" + method.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    private void executeAfterEach(PeriodicityEventsAlgorithmTest algoTest) {
        Method[] methods = algoTest.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (isMethodAnnotatedWithAfterEach(method)) {
                try {
                    method.invoke(algoTest);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error while trying to invoke method:" + method.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    public static Stream<Arguments> provideAlgorithmsAndExpectedResults() {
        return Stream.of(
                Arguments.of(new ValidAlgorithm(), false), // Correcta, no debe fallar
                Arguments.of(new AlwaysPeriodicityExceptionAlgorithm(), true), // Siempre lanza excepción
                Arguments.of(new DoNothingAlgorithm(), true), // No hace validación, debe fallar
                Arguments.of(new MissingCategoryCheckAlgorithm(), true), // Algoritmo incorrecto que no valida categoría
                Arguments.of(new WrongDaysCalculationAlgorithm(), true), // Algoritmo que calcula mal los días
                Arguments.of(new MissingFederationsCheckAlgorithm(), true) // Algoritmo que no valida federaciones
        );
    }

}
