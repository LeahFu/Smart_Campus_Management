package ca.access.student.service.impl;

import ca.access.student.repository.ScoresRepository;
import ca.access.student.service.IScoresService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Lei Fu
 * @date: 2023/06/17
 * @description: Implement grade management business interface
 */
@Service
@Transactional(readOnly = true)
public class ScoresServiceImpl implements IScoresService {
    private final ScoresRepository scoresRepository;
    public ScoresServiceImpl(ScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }
}
