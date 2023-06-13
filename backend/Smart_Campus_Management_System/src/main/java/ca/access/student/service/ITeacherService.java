package ca.access.student.service;

import ca.access.student.service.dto.TeacherQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/06/12
 * @description: Teacher information service interface
 */
public interface ITeacherService {
    /**
     * Get teacher list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    Object getList(TeacherQueryCriteria queryCriteria, Pageable pageable);
}
