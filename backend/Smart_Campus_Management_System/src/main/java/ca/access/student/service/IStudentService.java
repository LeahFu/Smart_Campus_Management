package ca.access.student.service;

import ca.access.student.domain.Student;
import ca.access.student.service.dto.StudentQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/06/01
 * @description: Student management service interface
 */
public interface IStudentService {
    /**
     * Get student list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    Object getList(StudentQueryCriteria queryCriteria, Pageable pageable);
    /**
     * Add student information
     * @param student
     * @return
     */
    boolean addStudent(Student student);
    /**
     * Get student details based on ID
     * @param id
     * @return
     */
    Student getById(Long id);

    /**
     * Update student information
     * @param student
     */
    void editStudent(Student student);
}
