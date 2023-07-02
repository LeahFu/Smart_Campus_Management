package ca.access.student.service.impl;

import ca.access.student.domain.Student;
import ca.access.student.domain.Course;
import ca.access.student.domain.Scores;
import ca.access.student.domain.GradeClass;
import ca.access.student.repository.ScoresRepository;
import ca.access.student.repository.StudentRepository;
import ca.access.student.service.IScoresService;
import ca.access.student.service.dto.ScoresQueryCriteria;
import ca.access.student.vo.BarEchartsSeriesModel;
import ca.access.student.vo.EchartsSeriesModel;
import ca.access.student.vo.RegisterScoresModel;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    /**
     * Update grades
     * @param scores
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editScores(Scores scores) {
        Scores dbScores = scoresRepository.getReferenceById(scores.getId());
        dbScores.setType("Corrected");
        BeanUtil.copyProperties(scores,dbScores, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        scoresRepository.save(dbScores);
    }
    /**
     * Delete grades information based on ID
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        scoresRepository.deleteById(id);
    }
    /**
     * Statistical class course grades
     * @param courseId
     * @param gradeClassId
     * @return
     */
    @Override
    public List<EchartsSeriesModel> getScoreCensus(Long courseId, Long gradeClassId) {
        List<EchartsSeriesModel> data = new ArrayList<>();
        // Get all grade information based on class ID and course ID
        List<Scores> scoresList = scoresRepository.findAllByCourseIdAndGradeClassId(courseId,gradeClassId);

        // The number of people whose grades are "A"
        AtomicInteger excellentNums= new AtomicInteger();
        EchartsSeriesModel excellentEchartsSeriesModel= new EchartsSeriesModel();
        // The number of people whose grades are "B"
        AtomicInteger goodNums = new AtomicInteger();
        EchartsSeriesModel goodEchartsSeriesModel= new EchartsSeriesModel();
        // The number of people whose grades are "C"
        AtomicInteger commonNums = new AtomicInteger();
        EchartsSeriesModel commonEchartsSeriesModel= new EchartsSeriesModel();
        // The number of people whose grades are "D"
        AtomicInteger badNums = new AtomicInteger();
        EchartsSeriesModel badEchartsSeriesModel= new EchartsSeriesModel();
        // The number of people whose grades are "F"
        AtomicInteger failNums = new AtomicInteger();
        EchartsSeriesModel failEchartsSeriesModel= new EchartsSeriesModel();

        scoresList.stream().forEach(item-> {
            if(item.getScore()>=90){
                excellentNums.getAndIncrement();
                excellentEchartsSeriesModel.setName("A");
                excellentEchartsSeriesModel.setValue(excellentNums.intValue());
            }else if(item.getScore()>=80&&item.getScore()<90) {
                goodNums.getAndIncrement();
                goodEchartsSeriesModel.setName("B");
                goodEchartsSeriesModel.setValue(goodNums.intValue());
            }else if(item.getScore()>=70&&item.getScore()<80) {
                commonNums.getAndIncrement();
                commonEchartsSeriesModel.setName("C");
                commonEchartsSeriesModel.setValue(commonNums.intValue());
            }else if(item.getScore()>=60&&item.getScore()<70) {
                badNums.getAndIncrement();
                badEchartsSeriesModel.setName("D");
                badEchartsSeriesModel.setValue(badNums.intValue());
            }else {
                failNums.getAndIncrement();
                failEchartsSeriesModel.setName("F");
                failEchartsSeriesModel.setValue(failNums.intValue());
            }
        });
        // The number of people whose grades are "A"
        if(excellentNums.intValue()!=0){
            data.add(excellentEchartsSeriesModel);
        }
        // The number of people whose grades are "B"
        if(goodNums.intValue()!=0){
            data.add(goodEchartsSeriesModel);
        }
        // The number of people whose grades are "C"
        if(commonNums.intValue()!=0){
            data.add(commonEchartsSeriesModel);
        }
        // The number of people whose grades are "D"
        if(badNums.intValue()!=0){
            data.add(badEchartsSeriesModel);
        }
        // The number of people whose grades are "F"
        if(failNums.intValue()!=0){
            data.add(failEchartsSeriesModel);
        }

        return data;
    }

    /**
     * Comparison of class course scores
     * @param courseId
     * @return
     */
    @Override
    public HashMap<String, Object> getScoresContrastCensus(Long courseId) {
        List<BarEchartsSeriesModel> barEchartsSeriesList = new ArrayList<>();
        // Get all grade records under this course
        List<Scores> scoresList = scoresRepository.findAllByCourseId(courseId);

        // Statistics of the maximum value, minimum value, count, sum and average information of the same group
        HashMap<GradeClass, DoubleSummaryStatistics> resultGradeClass = scoresList.stream()
                .collect(Collectors.groupingBy(Scores::getGradeClass, HashMap::new, Collectors.summarizingDouble(Scores::getScore)));
        // Average score
        List<Double> averageList = new ArrayList<>();
        // Highest score
        List<Double> maxList = new ArrayList<>();
        // Minimum score
        List<Double> minList = new ArrayList<>();
        // Total class size
        List<Double> countList = new ArrayList<>();
        // Class course total score
        List<Double> sumList = new ArrayList<>();

        // Abscissa
        List<String> categoryList = new ArrayList<>();
        resultGradeClass.forEach((k, v) -> {
            // Class name
            categoryList.add(k.getName());
            // Average score, keep two decimal places
            BigDecimal bigDecimal = new BigDecimal(v.getAverage());
            double average = bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
            averageList.add(average);
            // Highest score
            maxList.add(v.getMax());
            // Minimum score
            minList.add(v.getMin());
            // Total class size
            countList.add((double)v.getCount());
            // Class course total score
            sumList.add(v.getSum());
        });
        // Average score
        BarEchartsSeriesModel averageBarEchartsSeriesModel = new BarEchartsSeriesModel();
        averageBarEchartsSeriesModel.setData(averageList);
        averageBarEchartsSeriesModel.setType("bar");
        averageBarEchartsSeriesModel.setName("average score");
        barEchartsSeriesList.add(averageBarEchartsSeriesModel);
        // Highest score
        BarEchartsSeriesModel maxBarEchartsSeriesModel = new BarEchartsSeriesModel();
        maxBarEchartsSeriesModel.setData(maxList);
        maxBarEchartsSeriesModel.setType("bar");
        maxBarEchartsSeriesModel.setName("highest score");
        barEchartsSeriesList.add(maxBarEchartsSeriesModel);
        // Minimum score
        BarEchartsSeriesModel minBarEchartsSeriesModel = new BarEchartsSeriesModel();
        minBarEchartsSeriesModel.setData(minList);
        minBarEchartsSeriesModel.setType("bar");
        minBarEchartsSeriesModel.setName("minimum score");
        barEchartsSeriesList.add(minBarEchartsSeriesModel);
        // Total class size
        BarEchartsSeriesModel countBarEchartsSeriesModel = new BarEchartsSeriesModel();
        countBarEchartsSeriesModel.setData(countList);
        countBarEchartsSeriesModel.setType("bar");
        countBarEchartsSeriesModel.setName("total class size");
        barEchartsSeriesList.add(countBarEchartsSeriesModel);
        // Total score
        BarEchartsSeriesModel sumBarEchartsSeriesModel = new BarEchartsSeriesModel();
        sumBarEchartsSeriesModel.setData(sumList);
        sumBarEchartsSeriesModel.setType("bar");
        sumBarEchartsSeriesModel.setName("total score");
        barEchartsSeriesList.add(sumBarEchartsSeriesModel);

        // Define return object
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("categoryList",categoryList);
        resultMap.put("barEchartsSeriesList",barEchartsSeriesList);

        return resultMap;
    }

    /**
     * Comparison scores of all courses
     * @return
     */
    @Override
    public HashMap<String, Object> getAllSubjectScoreContrast() {
        return null;
    }
}
