package es.us.dp1.chess.federation.regulation;

import es.us.dp1.chess.federation.model.NamedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rule extends NamedEntity {

    @NotBlank
    @NotNull
    private String description;

    @NotNull
    private boolean active;

    @ManyToOne
    @NotNull
    Federation federation;
    

}
