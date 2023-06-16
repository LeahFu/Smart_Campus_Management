package ca.access.student.service;

import ca.access.student.domain.Teacher;
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

    /**
     * Add teacher information
     * @param teacher
     * @return
     */
    boolean addTeacher(Teacher teacher);

    /**
     * Get teacher details based on ID
     * @param id
     * @return
     */
    Teacher getById(Long id);

    /**
     * Update teacher information
     * @param teacher
     */
    void editTeacher(Teacher teacher);
}
