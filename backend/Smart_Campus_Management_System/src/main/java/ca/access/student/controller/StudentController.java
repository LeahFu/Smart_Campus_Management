package ca.access.student.controller;

import ca.access.student.service.IStudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lei Fu
 * @date: 2023/06/01
 * @description: Student management front controller
 */
@RestController
@RequestMapping("student")
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }
}
