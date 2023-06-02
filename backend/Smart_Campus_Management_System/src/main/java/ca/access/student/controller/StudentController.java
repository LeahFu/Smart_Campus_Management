package ca.access.student.controller;

import ca.access.student.service.dto.StudentQueryCriteria;
import ca.access.student.service.IStudentService;
import ca.access.utils.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
