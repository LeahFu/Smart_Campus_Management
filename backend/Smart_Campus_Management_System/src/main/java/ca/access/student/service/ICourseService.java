package ca.access.student.service;

import ca.access.student.service.dto.CourseQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course information business interface
 */
public interface ICourseService {
    /**
     * Get course list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    Object getList(CourseQueryCriteria queryCriteria, Pageable pageable);
}
