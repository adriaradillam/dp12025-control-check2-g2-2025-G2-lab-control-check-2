package es.us.dp1.chess.federation.regulation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class SanctionService {

    SanctionRepository sanctionRepository;

    @Autowired
    public SanctionService(SanctionRepository sanctionRepository) {
        this.sanctionRepository = sanctionRepository;
    }   

    @Transactional(readOnly = true)
    List<Sanction> findAll() {
        return sanctionRepository.findAll();
    }

    @Transactional(readOnly = true)
    Sanction findById(Integer id) {
        return sanctionRepository.findById(id).orElse(null);
    }

}
