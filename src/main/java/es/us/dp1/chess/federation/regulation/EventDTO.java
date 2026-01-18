package es.us.dp1.chess.federation.regulation;

import java.util.List;

import lombok.Getter;

@Getter
public class EventDTO {

    private String eventName;
    private List<String> participants;
    
    public EventDTO (String eventName, List<String> participants) {
        this.eventName = eventName;
        this.participants = participants;
    }
}
