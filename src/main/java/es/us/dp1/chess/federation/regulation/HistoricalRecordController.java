package es.us.dp1.chess.federation.regulation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/federation")
public class HistoricalRecordController {

    FederationService federationService;
    ChessEventService chessEventService;

    public HistoricalRecordController (FederationService federationService, ChessEventService chessEventService) {
        this.federationService = federationService;
        this.chessEventService = chessEventService;
    }

    @GetMapping("/{id}/records")
    public ResponseEntity<HistoryDTO> getRecordsForFederation (@PathVariable Integer id) {
        Federation f = federationService.getFederationById(id).orElse(null);
        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ChessEvent> ce = chessEventService.getChessEventsByFederation(id);
            return new ResponseEntity<>(new HistoryDTO(f, ce), HttpStatus.OK);
        }
    }
}
