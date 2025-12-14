package es.us.dp1.chess.federation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import es.us.dp1.chess.federation.regulation.ChessEventRepository;
import es.us.dp1.chess.federation.regulation.ChessEventService;
import es.us.dp1.chess.federation.regulation.Federation;
import es.us.dp1.chess.federation.user.User;

import java.time.LocalDate;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test4 extends ReflexiveTest{

    @Autowired
    ChessEventRepository repo;

    @Autowired
    EntityManager em;

    @Test
    void test4LowUpcomingParticipationsBasicCase() {
        List<Federation> feds = em.createQuery(
                "SELECT f FROM Federation f WHERE f.id IN (2,3,4)",
                Federation.class
        ).getResultList();

        LocalDate date = LocalDate.parse("2025-08-01");

        List<User> result = repo.findUsersWithLowUpcomingParticipations(feds, date, 4);

        assertEquals(4, result.size(), 
                "Expected exactly 4 users with low future participations");

        List<String> usernames = result.stream().map(User::getUsername).toList();

        assertThat(
            usernames,
            containsInAnyOrder("player1", "player5", "player8", "player9")
        );
    }

    @Test
    void test4ParticipantsThreshold() {
        List<Federation> feds = em.createQuery(
                "SELECT f FROM Federation f WHERE f.id IN (2,3,4)",
                Federation.class
        ).getResultList();

        LocalDate date = LocalDate.parse("2025-08-01");

        // Threshold = 1 → no user appears fewer than 1 time
        List<User> result = repo.findUsersWithLowUpcomingParticipations(feds, date, 1);

        assertEquals(0, result.size(),
                "No user should be returned because HAVING COUNT(ce) < 1 cannot be satisfied"); //************** */

        // Threshold = 10 → all participants should match (if needed, adjust based on your dataset)
        result = repo.findUsersWithLowUpcomingParticipations(feds, date, 1);

        List<String> usernames = result.stream().map(User::getUsername).toList();

        assertThat(
            usernames,
            containsInAnyOrder(
                // 
            )
        );
    }

    @Test
    void test4DateFilter() {
        List<Federation> feds = em.createQuery(
                "SELECT f FROM Federation f WHERE f.id IN (2,3,4)",
                Federation.class
        ).getResultList();

        LocalDate date = LocalDate.parse("2030-01-01");

        List<User> result = repo.findUsersWithLowUpcomingParticipations(feds, date, 4);

        assertEquals(0, result.size(),
                "No users should be returned because no future events exist after this date");
    }

    @Test
    void test4ServiceIntegration() throws Exception {
        ChessEventService service = new ChessEventService(repo);

        Method m = Arrays.stream(service.getClass().getMethods())
                .filter(x -> x.getName().equals("getUsersWithLowUpcomingParticipations"))
                .findFirst()
                .orElse(null);

        assertNotNull(m, 
                "Method getUsersWithLowUpcomingParticipations must exist in the service layer");

        List<Federation> feds = em.createQuery(
                "SELECT f FROM Federation f WHERE f.id IN (2,3,4)",
                Federation.class
        ).getResultList();

        LocalDate date = LocalDate.parse("2025-08-01");

        Object result = m.invoke(service, feds, date, 4);

        assertEquals(
            repo.findUsersWithLowUpcomingParticipations(feds, date, 4),
            result,
            "Service should delegate correctly to repository"
        );
    }
}
