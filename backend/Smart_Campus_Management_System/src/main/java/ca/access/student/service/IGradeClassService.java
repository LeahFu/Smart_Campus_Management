package ca.access.student.service;

import ca.access.student.domain.GradeClass;
import ca.access.student.service.dto.GradeClassQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/05/29
 * @description: Class information business interface
 */
public interface IGradeClassService {
    /**
     * Get class list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    Object getList(GradeClassQueryCriteria queryCriteria, Pageable pageable);

    /**
     * Add class information
     * @param gradeClass
     * @return
     */
    boolean addGradeClass(GradeClass gradeClass);

    /**
     * Get class details by ID
     * @param id
     * @return
     */
    GradeClass getById(Long id);
}
