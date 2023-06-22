package ca.access.student.repository;

import ca.access.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: Lei Fu
 * @date: 2023/06/01
 * @description: Student management persistence layer
 */
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    /**
     * Get all students under the class according to the class ID
     * @param gradeClassId
     * @return
     */
    List<Student> findAllByGradeClassId(Long gradeClassId);
}
