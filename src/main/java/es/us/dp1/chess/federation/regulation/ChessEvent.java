package es.us.dp1.chess.federation.regulation;

import java.time.LocalDate;
import java.util.List;

import es.us.dp1.chess.federation.model.NamedEntity;
import es.us.dp1.chess.federation.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ChessEvent extends NamedEntity {

    @NotNull
    LocalDate date;

    @Enumerated(EnumType.STRING)
    EventCategory category;

    @ManyToMany
    List<User> participant;

    @ManyToMany
    List<Rule> applies;

    @ManyToOne
    @JoinColumn(name = "federation_id")
    Federation organizedBy;

}
