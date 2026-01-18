package es.us.dp1.chess.federation.regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class HistoryDTO {

    private String federationData;
    private List<EventDTO> events;
    
    public HistoryDTO (Federation f, List<ChessEvent> ce) {
        this.federationData = f.getName() + " - " + f.getAcronym();
        List<EventDTO> eventsCons = new ArrayList<>();
        for (ChessEvent c: ce) {
            List<String> participants = c.getParticipant().stream().map(x -> x.getUsername()).collect(Collectors.toList());
            eventsCons.add(new EventDTO(c.getName(), participants));
        }
        this.events = eventsCons.isEmpty() ? null : eventsCons;
    }
}
