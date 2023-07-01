package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.student.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lei Fu
 * @date: 2023/07/01
 * @description: Background home page statistics
 */
@RestController
@RequestMapping("home")
public class HomeController {
    private final IStudentService studentService;
    private final IGradeClassService gradeClassService;
    private final ITeacherService teacherService;
    private final ICourseService courseService;

    public HomeController(IStudentService studentService, IGradeClassService gradeClassService, ITeacherService teacherService, ICourseService courseService) {
        this.studentService = studentService;
        this.gradeClassService = gradeClassService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    /**
     * Background home page statistics
     * @return
     */
    @GetMapping
    public BaseResult getIndexTotal(){
        Map<String,Object> resultMap = new HashMap<>();
        // Statistical student population
        long studentNums = studentService.getCount();
        resultMap.put("studentNums",studentNums);

        // Statistics the number of classes
        long classNums = gradeClassService.getCount();
        resultMap.put("classNums",classNums);

        // Statistics the number of teachers
        long teacherNums = teacherService.getCount();
        resultMap.put("teacherNums",teacherNums);

        // Statistics the number of courses
        long courseNums = courseService.getCount();
        resultMap.put("courseNums",courseNums);

        return BaseResult.success(resultMap);
    }
}
