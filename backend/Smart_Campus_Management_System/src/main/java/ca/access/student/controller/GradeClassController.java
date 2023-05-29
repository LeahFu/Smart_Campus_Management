package ca.access.student.controller;

import ca.access.student.service.IGradeClassService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lei Fu
 * @date: 2023/05/29
 * @description: Class information front controller
 */
@RestController
@RequestMapping("gradeclass")
public class GradeClassController {
    private final IGradeClassService gradeClassService;

    public GradeClassController(IGradeClassService gradeClassService) {
        this.gradeClassService = gradeClassService;
    }
}
