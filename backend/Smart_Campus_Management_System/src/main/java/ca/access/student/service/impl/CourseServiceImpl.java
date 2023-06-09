package ca.access.student.service.impl;

import ca.access.student.repository.CourseRepository;
import ca.access.student.service.ICourseService;
import ca.access.student.service.dto.CourseQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
