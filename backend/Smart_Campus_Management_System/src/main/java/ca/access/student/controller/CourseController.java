package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.student.domain.Course;
import ca.access.student.service.ICourseService;
import ca.access.student.service.dto.CourseQueryCriteria;
import ca.access.utils.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /**
     * Get course list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(CourseQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1,pageVo.getPageSize(), Sort.Direction.DESC, "id");
        return new ResponseEntity<>(courseService.getList(queryCriteria,pageable), HttpStatus.OK);
    }
    /**
     * Add course information
     * @param course
     * @return
     */
    @PostMapping
    public BaseResult addCourse(@RequestBody Course course){
        boolean result= courseService.addCourse(course);
        if(result){
            return BaseResult.success("Added successfully");
        }else {
            return BaseResult.fail("Add failed");
        }
    }
}