package ca.access.student.service;

import ca.access.student.service.dto.ScoresQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/06/17
 * @description: Grade management business interface
 */
public interface IScoresService {
    /**
     * Get grades list
     * @param queryCriteria
     * @param pageable
     * @return
     */
    Object getList(ScoresQueryCriteria queryCriteria, Pageable pageable);
}
