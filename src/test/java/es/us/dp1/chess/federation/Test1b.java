package es.us.dp1.chess.federation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.federation.regulation.Referee;
import es.us.dp1.chess.federation.regulation.Sanction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.ManyToOne;

@DataJpaTest
public class Test1b extends ReflexiveTest {

    @Autowired
    EntityManager em;

    @Test
    public void test1bRefereeFederationAnnotation() {
        checkThatFieldIsAnnotatedWith(Referee.class, "certifiedBy", ManyToOne.class);
        checkThatFieldIsAnnotatedWith(Referee.class, "certifiedBy", NotNull.class);
    }

    @Test
    public void test1bRefereeChessEventAnnotation() {
        checkThatFieldIsAnnotatedWith(Referee.class, "assignedTo", ManyToMany.class);
        checkThatFieldIsNotAnnotatedWith(Referee.class, "assignedTo", NotNull.class);
    }

    @Test
    public void test1bSanctionRefereeEventAnnotation() {
        checkThatFieldIsAnnotatedWith(Sanction.class, "imposedBy", ManyToOne.class);
        checkThatFieldIsAnnotatedWith(Sanction.class, "imposedBy", NotNull.class);
    }

    @Test
    public void test1bSanctionUserEventAnnotation() {
        checkThatFieldIsAnnotatedWith(Sanction.class, "imposedOn", ManyToOne.class);
        checkThatFieldIsAnnotatedWith(Sanction.class, "imposedOn", NotNull.class);
    }

    @Test
    public void test1bSanctionRuleEventAnnotation() {
        checkThatFieldIsAnnotatedWith(Sanction.class, "ruleBroken", ManyToOne.class);
        checkThatFieldIsNotAnnotatedWith(Sanction.class, "ruleBroken", NotNull.class);
    }

}
