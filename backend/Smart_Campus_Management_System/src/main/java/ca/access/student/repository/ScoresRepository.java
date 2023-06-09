package ca.access.student.repository;

import ca.access.student.domain.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: Lei Fu
 * @date: 2023/06/17
 * @description: Grade Management Persistence Layer
 */
public interface ScoresRepository extends JpaRepository<Scores, Long>, JpaSpecificationExecutor<Scores> {
    /**
     * Query score information based on course ID and student ID
     * @param courseId
     * @param id
     * @return
     */
    Scores getCourseByCourseIdAndStudentId(Long courseId, Long id);

    /**
     * Get a list of grades based on class ID and course ID
     * @param courseId
     * @param gradeClassId
     * @return
     */
    List<Scores> findAllByCourseIdAndGradeClassId(Long courseId, Long gradeClassId);

    /**
     * Get all grade records based on course ID
     * @param courseId
     * @return
     */
    List<Scores> findAllByCourseId(Long courseId);
}
