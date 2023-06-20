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

}