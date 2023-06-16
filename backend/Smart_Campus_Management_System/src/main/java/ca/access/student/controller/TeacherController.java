package ca.access.student.controller;

import ca.access.exception.BadRequestException;
import ca.access.student.domain.Teacher;
import ca.access.base.BaseResult;
import ca.access.student.service.dto.TeacherQueryCriteria;
import ca.access.student.service.ITeacherService;
import ca.access.utils.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /**
     * Get teacher list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(TeacherQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1,pageVo.getPageSize(), Sort.Direction.DESC, "id");
        return new ResponseEntity<>(teacherService.getList(queryCriteria,pageable), HttpStatus.OK);
    }
    /**
     * Add teacher information
     * @param teacher
     * @return
     */
    @PostMapping
    public BaseResult addTeacher(@RequestBody Teacher teacher){
        boolean result= teacherService.addTeacher(teacher);
        if(result){
            return BaseResult.success("Added successfully");
        }else {
            return BaseResult.fail("Add failed");
        }
    }
    /**
     * Get teacher details based on ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to get information");
        }
        Teacher dbTeacher = teacherService.getById(id);
        return BaseResult.success(dbTeacher);
    }
    /**
     * Update teacher information
     * @param teacher
     * @return
     */
    @PutMapping
    public BaseResult editTeacher(@RequestBody Teacher teacher){
        teacherService.editTeacher(teacher);
        return BaseResult.success("Update completed");
    }
}
