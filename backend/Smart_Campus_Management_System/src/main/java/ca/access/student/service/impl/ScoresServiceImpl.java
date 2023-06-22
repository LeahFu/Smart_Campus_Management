package ca.access.student.service.impl;

import ca.access.student.domain.Student;
import ca.access.student.domain.Course;
import ca.access.student.domain.Scores;
import ca.access.student.domain.GradeClass;
import ca.access.student.repository.ScoresRepository;
import ca.access.student.repository.StudentRepository;
import ca.access.student.service.IScoresService;
import ca.access.student.service.dto.ScoresQueryCriteria;
import ca.access.student.vo.RegisterScoresModel;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Lei Fu
 * @date: 2023/06/17
 * @description: Implement grade management business interface
 */
@Service
@Transactional(readOnly = true)
public class ScoresServiceImpl implements IScoresService {
    private final ScoresRepository scoresRepository;
    private final StudentRepository studentRepository;
    public ScoresServiceImpl(ScoresRepository scoresRepository,StudentRepository studentRepository) {
        this.scoresRepository = scoresRepository;
        this.studentRepository = studentRepository;
    }
    /**
     * Get grades list
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(ScoresQueryCriteria queryCriteria, Pageable pageable) {
        Page<Scores> page = scoresRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }
    /**
     * Register class course grades
     * @param scoresModel
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerScores(RegisterScoresModel scoresModel) {

        // Get all students under the class according to the class ID
        List<Student> studentList = studentRepository.findAllByGradeClassId(scoresModel.getGradeClassId());

        for(Student student: studentList){
            // Query score information based on course ID and student ID
            Scores dbScores = scoresRepository.getCourseByCourseIdAndStudentId(scoresModel.getCourseId(),student.getId());
            // The student has no grades registered in this course
            if(dbScores==null){
                // Add items
                dbScores = new Scores();
                dbScores.setType("Uncorrected");
                dbScores.setScore(0);
                dbScores.setRemarks("Initial score");
                dbScores.setStudent(student);
                Course tempCourse = new Course();
                // Course
                tempCourse.setId(scoresModel.getCourseId());
                dbScores.setCourse(tempCourse);
                // Class
                GradeClass gradeClass = new GradeClass();
                gradeClass.setId(scoresModel.getGradeClassId());
                dbScores.setGradeClass(gradeClass);
                scoresRepository.save(dbScores);
            }
        }
    }
}
