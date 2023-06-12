package ca.access.student.repository;

import ca.access.student.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Lei Fu
 * @date: 2023/06/12
 * @description: Teacher management persistence layer
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
}
