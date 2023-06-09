package ca.access.student.repository;

import ca.access.student.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course information persistence layer
 */
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
}
