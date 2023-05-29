package ca.access.student.repository;

import ca.access.student.domain.GradeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Lei Fu
 * @date: 2023/05/29
 * @description: Class management persistence layer
 */
public interface GradeClassRepository extends JpaRepository<GradeClass, Long>, JpaSpecificationExecutor<GradeClass> {
}
