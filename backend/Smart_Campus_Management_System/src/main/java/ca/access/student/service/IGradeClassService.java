package ca.access.student.service;

import ca.access.student.domain.GradeClass;
import ca.access.student.service.dto.GradeClassQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    /**
     * Update class information
     * @param gradeClass
     */
    void editGradeClass(GradeClass gradeClass);

    /**
     * Delete class information based on ID
     * @param id
     */
    void deleteById(Long id);

    /**
     * Get information of all classes
     * @param gradeClassQueryCriteria
     * @return
     */
    List<GradeClass> queryAll(GradeClassQueryCriteria gradeClassQueryCriteria);

    /**
     * Statistics class number
     * @return
     */
    long getCount();
}
