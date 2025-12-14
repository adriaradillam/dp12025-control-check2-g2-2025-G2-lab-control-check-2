package es.us.dp1.chess.federation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.federation.regulation.Federation;
import es.us.dp1.chess.federation.regulation.Referee;
import es.us.dp1.chess.federation.regulation.Sanction;
import es.us.dp1.chess.federation.user.User;
import es.us.dp1.chess.federation.regulation.Rule;
import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test2 extends ReflexiveTest{

    @Autowired
    EntityManager em;  

    @Test
    public void test2InicitialReferees(){

        // ==== REFEREE 1 ====
        Referee r1 = em.find(Referee.class, 1);
        assertNotNull("Referee with id 1 should exist", r1);

        assertEquals("John Peterson", getFieldValueReflexively(r1, "name"));
        assertEquals("REF2025001", r1.getLicenseNumber());
        assertEquals(LocalDate.of(2000, 1, 6), r1.getCertificationDate());

        // ==== REFEREE 2 ====
        Referee r2 = em.find(Referee.class, 2);
        assertNotNull("Referee with id 2 should exist", r2);

        assertEquals("María González", getFieldValueReflexively(r2, "name"));
        assertEquals("REF2025002", r2.getLicenseNumber());
        assertEquals(LocalDate.of(1998, 12, 14), r2.getCertificationDate());

    }

    @Test
    public void test2AdvancedReferees(){

        // ==== REFEREE 1 ====
        Referee r1 = em.find(Referee.class, 1);
        Federation f2 = em.find(Federation.class, 2);
        assertEquals(f2.getId(), r1.getCertifiedBy().getId(), "Referee 1 must be certified by Federation 2");

        // ==== REFEREE 2 ====
        Referee r2 = em.find(Referee.class, 2);
        Federation f3 = em.find(Federation.class, 3);
        assertEquals(f3.getId(), r2.getCertifiedBy().getId(),
                "Referee 2 must be certified by Federation 3");

    }


    @Test
    public void test2InitialSanctions() {

        // ==== SANCTION 1 ====
        Sanction s1 = em.find(Sanction.class, 1);
        assertNotNull("Sanction 1 should exist", s1);

        assertEquals("Unsportsmanlike conduct. Disrespect toward an opponent.", s1.getDescription());
        assertEquals(500.0, s1.getMonetaryFine());
        assertEquals("EXPULSION", s1.getType().name());


        // ==== SANCTION 2 ====
        Sanction s2 = em.find(Sanction.class, 2);
        assertNotNull( "Sanction 2 should exist", s2);

        assertEquals("The player arrived late to the playing area.", s2.getDescription());
        assertEquals("WARNING", s2.getType().name());
        assertNull( "Sanction 2 should have no monetary fine", s2.getMonetaryFine());
        // assertNull( "Sanction 2 must have no rule associated", s2.getRuleBroken());

    }

    @Test
    public void test2AdvancedSanctions(){

        // ==== SANCTION 1 ====
        Sanction s1 = em.find(Sanction.class, 1);

        User u9 = em.find(User.class, 9);
        assertEquals(u9.getId(), s1.getImposedOn().getId(), "Sanction 1 must be imposed on User 9.");

        Rule rule36 = em.find(Rule.class, 36);
        assertEquals(rule36.getId(), s1.getRuleBroken().getId(), "Sanction 1 must be linked to Rule 36.");

        if (s1 == null || s1.getImposedBy() == null) {
            assertNull(s1 == null ? null : s1.getImposedBy());
        } 


        // ==== SANCTION 2 ====
        Sanction s2 = em.find(Sanction.class, 2);

        if (s2 == null || s2.getImposedBy() == null) {
           assertNull(s2 == null ? null : s2.getImposedBy());
        }

        User u6 = em.find(User.class, 6);
        assertEquals(u6.getId(), s2.getImposedOn().getId(), "Sanction 2 must be imposed on User 6.");

        assertNull( "Sanction 2 must have no rule associated", s2.getRuleBroken());

    }



    @Test
    public void test2RefereesAssignedToEvents() {

        checkContainsById(Referee.class, 1, "getAssignedTo", 3, em);
        checkContainsById(Referee.class, 1, "getAssignedTo", 4, em);
        checkContainsById(Referee.class, 1, "getAssignedTo", 5, em);
        checkContainsById(Referee.class, 1, "getAssignedTo", 6, em);

        checkContainsById(Referee.class, 2, "getAssignedTo", 3, em);
        checkContainsById(Referee.class, 2, "getAssignedTo", 4, em);
        checkContainsById(Referee.class, 2, "getAssignedTo", 7, em);

    }

}

