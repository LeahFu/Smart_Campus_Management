package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.student.service.dto.StudentQueryCriteria;
import ca.access.student.service.IStudentService;
import ca.access.student.domain.Student;
import ca.access.utils.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /**
     * Get student list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(StudentQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1,pageVo.getPageSize(), Sort.Direction.DESC, "id");
        return new ResponseEntity<>(studentService.getList(queryCriteria,pageable), HttpStatus.OK);
    }
    /**
     * Add student information
     * @param student
     * @return
     */
    @PostMapping
    public BaseResult addStudent(@RequestBody Student student){
        boolean result= studentService.addStudent(student);
        if(result){
            return BaseResult.success("Added successfully");
        }else {
            return BaseResult.fail("Add failed");
        }
    }
}
