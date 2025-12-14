package es.us.dp1.chess.federation.regulation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuleService {

    RuleRepository ruleRepository;

    @Autowired
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Transactional(readOnly = true)
    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Rule getRuleById(Integer id) {
        return ruleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Rule saveRule(Rule rule) {
        return ruleRepository.save(rule);
    }

}
