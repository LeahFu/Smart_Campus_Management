package ca.access.student.service;

import ca.access.student.domain.Scores;
import ca.access.student.service.dto.ScoresQueryCriteria;
import ca.access.student.vo.RegisterScoresModel;
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
    /**
     * Register class course grades
     * @param scoresModel
     * @return
     */
    void registerScores(RegisterScoresModel scoresModel);

    /**
     * Update grades
     * @param scores
     */
    void editScores(Scores scores);

    /**
     * Delete grades information based on ID
     * @param id
     */
    void deleteById(Long id);
}
