package es.us.dp1.chess.federation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import es.us.dp1.chess.federation.regulation.ChessEvent;
import es.us.dp1.chess.federation.regulation.Federation;
import es.us.dp1.chess.federation.regulation.Referee;
import es.us.dp1.chess.federation.regulation.RefereeRepository;
import es.us.dp1.chess.federation.regulation.Sanction;
import es.us.dp1.chess.federation.regulation.SanctionRepository;
import es.us.dp1.chess.federation.regulation.SanctionType;
import es.us.dp1.chess.federation.regulation.Rule;
import es.us.dp1.chess.federation.regulation.EventCategory;
import es.us.dp1.chess.federation.user.Authorities;
import es.us.dp1.chess.federation.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test1a extends ReflexiveTest{

    @Autowired(required = false)
    RefereeRepository refereeRepository;

    @Autowired(required = false)
    SanctionRepository sanctionRepository;

    @Autowired
    EntityManager em;

    // --------------------------------------------------------------------------------
    
    @Test
    public void Test1aCheckRefereeAnnotations() throws NoSuchFieldException, SecurityException {
        assertTrue(classIsAnnotatedWith(Referee.class,Entity.class));        
    }
    @Test
    public void test1aCheckSanctionAnnotations() throws NoSuchFieldException, SecurityException {
        assertTrue(classIsAnnotatedWith(Sanction.class,Entity.class));        
    }    

    // --------------------------------------------------------------------------------

    @Test
    public void test1aCheckRepositoriesExist() {
        assertNotNull(refereeRepository, "The Referee repository was not injected into the tests, its autowired value was null.");
        assertNotNull(sanctionRepository, "The Sanction repository was not injected into the tests, its autowired value was null.");
        test1aRefereeRepositoryContainsMethod();
        test1aSanctionRepositoryContainsMethod();
    }

    public void test1aRefereeRepositoryContainsMethod() {
        if (refereeRepository != null) {
            Object v = refereeRepository.findById(999);
            assertFalse(v != null && ((Optional<?>) v).isPresent(), //* */
                "No result (null) should be returned for a Referee that does not exist");
        } else
            fail("The Sanction repository was not injected into the tests, its autowired value was null");
    }

    public void test1aSanctionRepositoryContainsMethod() {
        if (sanctionRepository != null) {
            Object v = sanctionRepository.findById(999);
            assertFalse(v != null && ((Optional<?>) v).isPresent(),     //* */
                "No result (null) should be returned for a Sanction that does not exist");
        } else
            fail("The Sanction repository was not injected into the tests, its autowired value was null");
    }

    // --------------------------------------------------------------------------------

    @Test
    public void test1aCheckRefereeContraints() {

        Map<String, List<Object>> invalidValues = Map.of(
            "name", List.of("", "Jo", "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda."),
            "licenseNumber", List.of("123456789", "12345678901", "          ")
        );

        Referee r = createValidReferee(em);
        em.persist(r);

        checkThatFieldsAreMandatory(r, em, "licenseNumber", "certificationDate");
        checkThatValuesAreNotValid(r, invalidValues, em);
    }

    @Test
    public void test1aCheckSanctionConstraints() {

        Map<String, List<Object>> invalidValues = Map.of(
            "description", List.of("                    ", "Texto corto", "Esta descripción es tan larga que supera ampliamente los setenta caracteres permitidos en la regla."),
            "monetaryFine", List.of(-1.0, 0.0)
        );

        Sanction s = createValidSanction(em);
        em.persist(s);

        checkThatFieldsAreMandatory(s, em, "description", "type");
        checkThatValuesAreNotValid(s, invalidValues, em);
    }

    // --------------------------------------------------------------------------------

    public static Referee createValidReferee(EntityManager em) {
        Referee r = new Referee();
        r.setLicenseNumber("ABCD123456");
        r.setCertificationDate(LocalDate.of(2023, 1, 1));
        r.setCertifiedBy(createValidFederation(em));
        r.setAssignedTo(List.of(createValidChessEvent(em)));
        return r;
    }

    public static Federation createValidFederation(EntityManager em) {
        Federation f = new Federation();
        f.setAcronym("FIDE");
        f.setName("Federación Internacional de Ajedrez");
        f.setDescription("Es una organización internacional que agrupa las diversas federaciones nacionales de ajedrez. ");
        f.setFoundationalDate(LocalDate.of(1924, 7, 20));
        em.persist(f);
        return f;
    }

    public static Rule createValidRule(EntityManager em, String ruleName) {
        Rule rule = new Rule();
        rule.setName(ruleName);
        rule.setDescription("El objetivo de cada jugador es situar al rey adversario bajo ataque, de tal forma que el adversario no disponga de ninguna jugada legal.");
        rule.setActive(true);
        rule.setFederation(createValidFederation(em));

        return rule;
    }   

    public static ChessEvent createValidChessEvent(EntityManager em) {
        ChessEvent e = new ChessEvent();
        e.setName("Campeonato Mundial de Ajedrez 2025");
        e.setDate(LocalDate.of(2025, 12, 9));
        e.setCategory(EventCategory.WORLD);
        e.setParticipant(List.of(createValidParticipant(em, "Magnus Carlsen"), createValidParticipant(em, "Ian Nepomniachtchi")));
        e.setApplies(List.of());
        return e;
    }

    public static User createValidParticipant(EntityManager em, String name){
        Authorities a1=new Authorities();
        a1.setAuthority("PLAYER");
        em.persist(a1);

        User u1=new User();
        setValue(u1,"username",String.class,name);
        setValue(u1, "authority", Authorities.class, a1);
        return u1;
    }

    public static Sanction createValidSanction(EntityManager em) {
        Sanction s = new Sanction();
        s.setDescription("Suspensión de 3 años por uso de dispositivo electrónico.");
        s.setType(SanctionType.PENALTY_TIME);
        s.setMonetaryFine(0.01);
        
        Referee r = createValidReferee(em);
        em.persist(r);
        s.setImposedBy(r);

        User u = createValidParticipant(em, "Judit Polgar");
        em.persist(u);
        s.setImposedOn(u);

        Rule rule = createValidRule(em, "Uso de Dispositivos Electrónicos");
        em.persist(rule);
        s.setRuleBroken(rule);
        
        return s;
    }

}
