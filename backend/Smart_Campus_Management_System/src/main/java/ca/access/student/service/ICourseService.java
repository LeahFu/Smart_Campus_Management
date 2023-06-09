package ca.access.student.service;

import ca.access.student.service.dto.CourseQueryCriteria;
import org.springframework.data.domain.Pageable;
import ca.access.student.domain.Course;

import java.util.List;

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

    /**
     * Add course information
     * @param course
     * @return
     */
    boolean addCourse(Course course);
    /**
     * Get course information by id
     * @param id
     * @return
     */
    Course getById(Long id);

    /**
     * Update course information
     * @param course
     */
    void editCourse(Course course);
    /**
     * Delete course information based on ID
     * @param id
     * @return
     */
    void deleteById(Long id);

    /**
     * Get all courses
     * @return
     */
    List<Course> queryAll();

    /**
     * Statistics the number of teachers
     * @return
     */
    long getCount();
}
