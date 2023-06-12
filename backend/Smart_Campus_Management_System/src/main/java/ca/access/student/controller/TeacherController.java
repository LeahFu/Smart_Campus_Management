package ca.access.student.controller;

import ca.access.student.service.ITeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lei Fu
 * @date: 2023/06/12
 * @description: Teacher information front controller
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
