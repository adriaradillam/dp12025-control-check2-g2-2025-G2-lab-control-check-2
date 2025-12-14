package es.us.dp1.chess.federation.regulation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FederationService {

    FederationRepository federationRepository;

    @Autowired
    public FederationService(FederationRepository federationRepository){
        this.federationRepository = federationRepository;
    }

    @Transactional(readOnly = true)
    public List<Federation> getAllFederations(){
        return federationRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Federation> getFederationById(Integer id){
        return federationRepository.findById(id);
    }

    @Transactional
    public Federation saveFederation(Federation federation){
        return federationRepository.save(federation);
    }
    
}
