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

    public HomeController(IStudentService studentService, IGradeClassService gradeClassService) {
        this.studentService = studentService;
        this.gradeClassService = gradeClassService;
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

        // Statistics class number
        long classNums = gradeClassService.getCount();
        resultMap.put("classNums",classNums);
        return BaseResult.success(resultMap);
    }
}
