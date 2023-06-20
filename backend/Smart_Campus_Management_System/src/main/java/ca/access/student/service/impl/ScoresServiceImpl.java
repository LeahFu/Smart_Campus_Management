package ca.access.student.service.impl;

import ca.access.student.domain.Scores;
import ca.access.student.repository.ScoresRepository;
import ca.access.student.service.IScoresService;
import ca.access.student.service.dto.ScoresQueryCriteria;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    /**
     * Get grades list
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(ScoresQueryCriteria queryCriteria, Pageable pageable) {
        Page<Scores> page = scoresRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }
}
