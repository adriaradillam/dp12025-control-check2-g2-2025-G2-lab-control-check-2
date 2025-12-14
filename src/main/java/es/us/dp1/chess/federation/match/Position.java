package es.us.dp1.chess.federation.match;

import es.us.dp1.chess.federation.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Position extends BaseEntity {
    Integer x;
    Integer y;
}
