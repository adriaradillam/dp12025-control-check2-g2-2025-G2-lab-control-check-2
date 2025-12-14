package es.us.dp1.chess.federation.regulation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class RefereeService {

    RefereeRepository refereeRepository;

    @Autowired
    public RefereeService(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    @Transactional(readOnly = true)
    List<Referee> findAll() {
        return refereeRepository.findAll();
    }

    @Transactional(readOnly = true)
    Referee findById(Integer id) {
        return refereeRepository.findById(id).orElse(null);
    }

}
