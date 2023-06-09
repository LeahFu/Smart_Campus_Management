package ca.access.student.service.impl;

import ca.access.student.repository.CourseRepository;
import ca.access.student.service.ICourseService;
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
}
