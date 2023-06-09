package ca.access.student.controller;

import ca.access.student.service.ICourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course information front controller
 */
@RestController
@RequestMapping("course")
public class CourseController {
    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }
}
