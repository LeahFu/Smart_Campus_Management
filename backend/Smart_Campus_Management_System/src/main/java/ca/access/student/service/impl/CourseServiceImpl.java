package ca.access.student.service.impl;

import ca.access.student.domain.Course;
import ca.access.student.repository.CourseRepository;
import ca.access.student.service.ICourseService;
import ca.access.student.service.dto.CourseQueryCriteria;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course information business interface implementation class
 */
@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements ICourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Get course list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(CourseQueryCriteria queryCriteria, Pageable pageable) {
        Page<Course> page = courseRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }

    /**
     * Add course information
     * @param course
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCourse(Course course) {
        Course dbCourse = courseRepository.save(course);
        return dbCourse.getId()!=null;
    }
    /**
     * Get course information by id
     * @param id
     * @return
     */
    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseGet(Course::new);
    }
    /**
     * Update course information
     * @param course
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editCourse(Course course) {
        Course dbCourse = courseRepository.getReferenceById(course.getId());
        BeanUtil.copyProperties(course,dbCourse, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        courseRepository.save(dbCourse);
    }
    /**
     * Delete course information based on ID
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    /**
     * Get all courses
     * @return
     */
    @Override
    public List<Course> queryAll() {
        return courseRepository.findAll();
    }
    /**
     * Statistics the number of teachers
     * @return
     */
    @Override
    public long getCount() {
        return courseRepository.count();
    }
}
