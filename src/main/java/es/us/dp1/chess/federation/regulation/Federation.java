package es.us.dp1.chess.federation.regulation;

import java.time.LocalDate;

import es.us.dp1.chess.federation.model.NamedEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Federation extends NamedEntity {

    @NotNull
    String acronym;

    @NotNull
    @NotBlank
    String description;

    @NotNull
    LocalDate foundationalDate;

}
